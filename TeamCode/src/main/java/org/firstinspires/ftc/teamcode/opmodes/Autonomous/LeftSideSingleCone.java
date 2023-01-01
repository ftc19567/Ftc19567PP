package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.left;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagFirstId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagSecondId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagThirdId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.MecanumDriveCancelable;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.pipelines.AprilTagPipeline;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.LOCATION;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Disabled
@Autonomous(group = "official")
public class LeftSideSingleCone extends LinearOpMode
{
    private LOCATION location = LOCATION.FIRST;
    private TrajectorySequence complete;

    OpenCvCamera camera;
    AprilTagPipeline aprilTagDetectionPipeline;

    Claw claw;
    SimpleBotVerticalSlide verticalSlide;


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


    AprilTagDetection tagOfInterest = null;

    Pose2d startPose = left;

    @Override
    public void runOpMode() throws InterruptedException
    {

        MecanumDriveCancelable drive = new MecanumDriveCancelable(hardwareMap);
        drive.setPoseEstimate(startPose);

        claw = new Claw(hardwareMap, telemetry);
        verticalSlide = new SimpleBotVerticalSlide(hardwareMap, telemetry);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
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

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tagFirstId == tag.id || tagSecondId == tag.id || tagThirdId == tag.id)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
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
                        tagToTelemetry(tagOfInterest);
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
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();

            TrajectorySequence third = drive.trajectorySequenceBuilder(startPose)
                    .addTemporalMarker(0,() -> {
                        claw.close();
                    })
                    .waitSeconds(2)
                    .lineTo(new Vector2d(58,-10))
                    .waitSeconds(1)
                    .lineTo(new Vector2d(20,-12.5))
                    .addSpatialMarker(new Vector2d(56,-15),() -> {
                        verticalSlide.setPosition(verticalSpeed, 3130);

                    })
                    .splineTo(new Vector2d(8,-16), Math.toRadians(-135))//line up to pole
                    .addSpatialMarker(new Vector2d(8,-16), () -> {
                        claw.open();
                    })
                    .waitSeconds(2)
                    .lineToLinearHeading(new Pose2d(13, -10, Math.toRadians(-90)))
                    .addSpatialMarker(new Vector2d(62, -25), () -> {
                        verticalSlide.setPosition(verticalSpeed,3130);
                    })
                    .lineToLinearHeading(new Pose2d(35, -12, Math.toRadians(270)))
                    .build();

            TrajectorySequence second = drive.trajectorySequenceBuilder(startPose)
                    .addTemporalMarker(0,() -> {
                        claw.close();
                    })
                    .waitSeconds(2)
                    .lineTo(new Vector2d(58,-10))
                    .waitSeconds(1)
                    .lineTo(new Vector2d(20,-12.5))
                    .addSpatialMarker(new Vector2d(56,-15),() -> {
                        verticalSlide.setPosition(verticalSpeed, 3130);

                    })
                    .splineTo(new Vector2d(8,-16), Math.toRadians(-135))//line up to pole
                    .addSpatialMarker(new Vector2d(8,-16), () -> {
                        claw.open();
                    })
                    .waitSeconds(2)
                    .lineToLinearHeading(new Pose2d(13, -10, Math.toRadians(270)))
                    .addSpatialMarker(new Vector2d(13, -35), () -> {
                        verticalSlide.setPosition(verticalSpeed,3130);
                    })
                    .lineToLinearHeading(new Pose2d(13,-35,Math.toRadians(270)))
                    .turn(Math.toRadians(90))
                    .build();

            TrajectorySequence first = drive.trajectorySequenceBuilder(startPose)
                    .addTemporalMarker(0,() -> {
                        claw.close();
                    })
                    .waitSeconds(2)
                    .lineTo(new Vector2d(58,-10))
                    .waitSeconds(1)
                    .lineTo(new Vector2d(20,-12.5))
                    .addSpatialMarker(new Vector2d(56,-15),() -> {
                        verticalSlide.setPosition(verticalSpeed, 3130);

                    })
                    .splineTo(new Vector2d(8,-16), Math.toRadians(-135))//line up to pole
                    .addSpatialMarker(new Vector2d(8,-16), () -> {
                        claw.open();
                    })
                    .waitSeconds(2)
                    .lineToLinearHeading(new Pose2d(13, -10, Math.toRadians(270)))
                    .addSpatialMarker(new Vector2d(13, -35), () -> {
                        verticalSlide.setPosition(verticalSpeed,3130);
                    })
                    .lineToLinearHeading(new Pose2d(13,-54,Math.toRadians(270)))
                    .turn(Math.toRadians(90))
                    .build();

            /* Actually do something useful */
            if(tagOfInterest == null || tagOfInterest.id == tagFirstId)
            {
                location = LOCATION.FIRST;
            }
            else if (tagOfInterest.id == tagSecondId)
            {
                location = LOCATION.SECOND;
            }
            else if (tagOfInterest.id == tagThirdId)
            {
                location = LOCATION.THIRD;
            }

            switch(location) {

                case FIRST: {

                    complete = first;
                    telemetry.addData("OpenCV", "Pos 1 Detected");
                    telemetry.update();
                    break;
                }

                case SECOND: {
                    complete = second;
                    telemetry.addData("OpenCV", "Pos 2 Detected");
                    telemetry.update();
                    break;
                }
                case THIRD: {
                    complete = third;
                    telemetry.addData("OpenCV", "Pos 3 Detected you are poopoo");
                    telemetry.update();
                    break;
                }
            }
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }



        if (!isStopRequested())
            drive.followTrajectorySequence(complete);
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
    }
}
