package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.right;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.MecanumDriveCancelable;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.pipelines.AprilTagPipeline;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.AUTO_STATE;
import org.firstinspires.ftc.teamcode.util.LOCATION;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name = "Right Side", group = "official")
public class RightSideAutoFSM extends LinearOpMode {

    private TrajectorySequence complete;

    OpenCvCamera camera;
    AprilTagPipeline aprilTagDetectionPipeline;


    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 0;
    int MIDDLE = 1;
    int RIGHT = 2;

    AprilTagDetection tagOfInterest = null;

    Claw claw; 
    SimpleBotVerticalSlide verticalSlide;

    Pose2d startPose = right;

    double parkX;
    double parkY;

    AUTO_STATE currentState = AUTO_STATE.DETECTING_OPENCV;
    private LOCATION location = LOCATION.SECOND;

    int cycles = 0;
    int stackHeight = 178;

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        MecanumDriveCancelable drive = new MecanumDriveCancelable(hardwareMap);
        drive.setPoseEstimate(startPose);

        claw = new Claw(hardwareMap, telemetry);
        verticalSlide = new SimpleBotVerticalSlide(hardwareMap, telemetry);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        //TODO: edit while loop
        while(!opModeIsActive()) {
            claw.close();
            telemetry.addData("Tag",location); //Inform the driver of the detected location

            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    telemetry.addLine(String.format("\nDetected tag ID=%d", tagOfInterest.id));
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        telemetry.addLine(String.format("\nDetected tag ID=%d", tagOfInterest.id));
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    telemetry.addLine(String.format("\nDetected tag ID=%d", tagOfInterest.id));
                }

            }

            telemetry.update();
            sleep(20);
        }

        if(!opModeIsActive() || isStopRequested()) return;

        TrajectorySequence preloadSeq = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    claw.close();
                })
                .addSpatialMarker(new Vector2d(25,-62),() -> {
                    verticalSlide.setPosition(verticalSpeed, 1080);

                })
                .splineTo(new Vector2d(30,-7), Math.toRadians(131))
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    verticalSlide.setPosition(verticalSpeed, 890);
                    sleep(100);
                    claw.open();
                })
                .build();
        TrajectorySequence grabCone = drive.trajectorySequenceBuilder(preloadSeq.end())
                .addDisplacementMarker(() -> {
                    verticalSlide.setPosition(verticalSpeed,stackHeight);
                    stackHeight -= 30;
                })
                .lineToLinearHeading(new Pose2d(32,-13, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(62,-12.7, Math.toRadians(0)))
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    claw.close();
                })
                .build();
        TrajectorySequence deliverCone = drive.trajectorySequenceBuilder(grabCone.end())
                .addDisplacementMarker(() -> {
                    verticalSlide.setPosition(verticalSpeed, 1080);
                })
                .lineToLinearHeading(new Pose2d(35,-12.7, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(21,-11, Math.toRadians(90)))
                .addTemporalMarker(() -> {
                    verticalSlide.setPosition(verticalSpeed, 890);
                    sleep(100);
                    claw.open();
                })
                .waitSeconds(5)
                .build();
        /*TrajectorySequence park = drive.trajectorySequenceBuilder(deliverCone.end())
                        .build();
         */
        waitForStart();

        /* Actually do something useful */
        if(tagOfInterest == null){
            //default trajectory here if preferred
            location = LOCATION.SECOND;
        }else if(tagOfInterest.id == LEFT){
            //left trajectory
            location = LOCATION.FIRST;
        }else if(tagOfInterest.id == MIDDLE){
            //middle trajectory
            location = LOCATION.SECOND;
        }else{
            //right trajectory
            location = LOCATION.THIRD;
        }

        //TODO: Figure out positions
        switch (location){
            case FIRST:
                parkX = 0;
                parkY = 0;
                telemetry.addData("Parking Position","First Position");
                telemetry.update();
                break;
            case SECOND:
                parkX = 0;
                parkY = 0;
                telemetry.addData("Parking Position","Second Position");
                telemetry.update();
                break;
            case THIRD:
                parkX = 0;
                parkY = 0;
                telemetry.addData("Parking Position","Third Position");
                telemetry.update();
                break;
        }

        currentState = AUTO_STATE.TRAVELING_TO_POLE;


        drive.followTrajectorySequenceAsync(preloadSeq);

        master:while(opModeIsActive() && !isStopRequested()){

            switch (currentState) {
                case TRAVELING_TO_DEFAULT:
                    if(!drive.isBusy()){
                        telemetry.addData("State Machine","Preload Travel");
                        currentState = AUTO_STATE.DELIVERING_CONE;
                    }
                    break;
                case TRAVELING_TO_STACK:
                    if(!drive.isBusy()){
                        cycles++;
                        telemetry.addData("State Machine","Traveling to Stack");
                        currentState = AUTO_STATE.COLLECTING_CONE;
                        drive.followTrajectorySequenceAsync(deliverCone);
                    }
                    break;
                case COLLECTING_CONE:
                    //TODO: add time out for drop
                    telemetry.addData("State Machine","Collecting Cone");
                    currentState = AUTO_STATE.TRAVELING_TO_POLE;
                    break;
                case TRAVELING_TO_POLE:
                        telemetry.addData("State Machine","Traveling to Pole");
                        telemetry.update();
                        currentState = AUTO_STATE.DELIVERING_CONE;
                    break;
                case DELIVERING_CONE:
                    //TODO: add time out for drop
                    telemetry.addData("State Machine","Delivering cone");
                    if(!drive.isBusy()) {
                        if (cycles >= 1) {
                            currentState = AUTO_STATE.PARKING;
                            //drive.followTrajectorySequenceAsync(park);
                        } else {
                            currentState = AUTO_STATE.TRAVELING_TO_STACK;
                            drive.followTrajectorySequenceAsync(grabCone);
                        }
                    }
                    break;
                case PARKING:
                        telemetry.addData("State Machine","Park");
                        currentState = AUTO_STATE.COMPLETE;
                        break master;
                default:
                    currentState = AUTO_STATE.TRAVELING_TO_DEFAULT;

            }


            Pose2d poseEstimate = drive.getPoseEstimate();

            drive.update();

            telemetry.addData("Pose X",poseEstimate.getX());
            telemetry.addData("Pose Y",poseEstimate.getY());
            telemetry.addData("Pose Heading",poseEstimate.getHeading());

            telemetry.addData("IntakePosition", claw.getPos());
            telemetry.addData("Position", verticalSlide.getPosition());

            telemetry.addData("State", currentState);
            telemetry.update();

        }

        telemetry.addData("Status", "Path Complete");
        telemetry.update();

    }

}
