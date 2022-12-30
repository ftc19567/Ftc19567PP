package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.bottomLeft;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos1;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.topRight;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.pipelines.AprilTagPipeline;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.AUTO_STATE;
import org.firstinspires.ftc.teamcode.util.LOCATION;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(group = "official")
public class AutoOpModeFSM extends LinearOpMode {

    private TrajectorySequence complete;

    OpenCvCamera camera;
    AprilTagPipeline aprilTagDetectionPipeline;

    Claw claw; 
    SimpleBotVerticalSlide verticalSlide;

    Pose2d startPose = topRight;

    double parkX;
    double parkY;

    AUTO_STATE currentState = AUTO_STATE.DETECTING_OPENCV;
    private LOCATION location = LOCATION.SECOND;

    int cycles = 0;

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        claw = new Claw(hardwareMap, telemetry);
        verticalSlide = new SimpleBotVerticalSlide(hardwareMap, telemetry);

        //TODO: edit while loop
        while(!opModeIsActive()) {
            claw.close();
            telemetry.addData("Tag",location); //Inform the driver of the detected location
            telemetry.update();
        }

        if(!opModeIsActive() || isStopRequested()) return;

        waitForStart();
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

        TrajectorySequence preloadSeq = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    claw.close();
                })
                .addSpatialMarker(new Vector2d(25,-62),() -> {
                    verticalSlide.setPosition(verticalSpeed, slidePos1);

                })
                .splineTo(new Vector2d(35,-15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(18,-12), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    verticalSlide.setPosition(verticalSpeed, 2500);
                    sleep(500);
                    claw.open();
                })
                .waitSeconds(2)
                .build();
        /*TrajectorySequence grabCone = drive.trajectorySequenceBuilder(preloadSeq.end())
                        .build();
        TrajectorySequence deliverCone = drive.trajectorySequenceBuilder(grabCone.end())
                        .build();
        TrajectorySequence park = drive.trajectorySequenceBuilder(deliverCone.end())
                        .build();
         */

        drive.followTrajectorySequenceAsync(preloadSeq);

        master:while(opModeIsActive() && !isStopRequested()){

            switch (currentState) {
                case TRAVELING_TO_DEFAULT:
                    if(!drive.isBusy()){
                        telemetry.addData("State Machine","Preload Travel");
                        telemetry.update();
                        currentState = AUTO_STATE.DELIVERING_CONE;
                    }
                    break;
                case TRAVELING_TO_STACK:
                    if(!drive.isBusy()){
                        cycles++;
                        telemetry.addData("State Machine","Traveling to Stack");
                        telemetry.update();
                        currentState = AUTO_STATE.COLLECTING_CONE;
                    }
                    break;
                case COLLECTING_CONE:
                    //TODO: add time out for drop
                    telemetry.addData("State Machine","Collecting Cone");
                    telemetry.update();
                    currentState = AUTO_STATE.TRAVELING_TO_POLE;
                    //drive.followTrajectorySequenceAsync(deliverCone);
                    break;
                case TRAVELING_TO_POLE:
                        telemetry.addData("State Machine","Traveling to Pole");
                        telemetry.update();
                        currentState = AUTO_STATE.DELIVERING_CONE;
                    break;
                case DELIVERING_CONE:
                    //TODO: add time out for drop
                    telemetry.addData("State Machine","Delivering cone");
                    telemetry.update();
                    if(cycles == 5){
                        currentState = AUTO_STATE.PARKING;
                        //drive.followTrajectorySequenceAsync(park);
                    }
                    else{
                        currentState = AUTO_STATE.TRAVELING_TO_STACK;
                        //drive.followTrajectorySequenceAsync(grabCone);
                    }
                    break;
                case PARKING:
                        telemetry.addData("State Machine","Park");
                        telemetry.update();
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
