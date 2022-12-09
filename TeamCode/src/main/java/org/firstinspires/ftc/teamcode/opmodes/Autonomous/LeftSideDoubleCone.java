package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.bottomLeft;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos1;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagFirstId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagSecondId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagThirdId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.pipelines.AprilTagPipeline;
import org.firstinspires.ftc.teamcode.pipelines.LOCATION;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(group = "official")
public class LeftSideDoubleCone extends LinearOpMode
{
    private LOCATION location = LOCATION.FIRST;
    private TrajectorySequence complete;

    OpenCvCamera camera;
    AprilTagPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

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

    Pose2d startPose = bottomLeft;

    SimpleBotVerticalSlide slides;
    Claw claw;

    @Override
    public void runOpMode() throws InterruptedException
    {
        slides = new SimpleBotVerticalSlide(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

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
            sleep(20);
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

        TrajectorySequence first = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(58,-10))
                .lineTo(new Vector2d(20,-12.5))
                .splineTo(new Vector2d(8,-16), Math.toRadians(-135)) //drop cone
                .addDisplacementMarker(() -> {
                    slides.setPosition(verticalSpeed,slidePos1);
                })

                .waitSeconds(1)
                .addDisplacementMarker(() -> {
                    slides.setPosition(verticalSpeed, 0);
                })
                .lineToLinearHeading(new Pose2d(15, -10, Math.toRadians(-135)))
                .lineToLinearHeading(new Pose2d(36, -10, Math.toRadians(270)))
                //go to next cone
                .lineTo(new Vector2d(37,-30))
                //drop cone
                .waitSeconds(1)
                .splineTo(new Vector2d(5,-28), Math.toRadians(135))
                .waitSeconds(1)
                .addSpatialMarker(new Vector2d(36, -36), () -> {
                    slides.setPosition(verticalSpeed,slidePos1);
                })
                //reset
                .waitSeconds(1)
                .addDisplacementMarker(() -> {
                    slides.setPosition(verticalSpeed, 0);
                })
                .lineToLinearHeading(new Pose2d(12, -36, Math.toRadians(90)))
                .splineTo(new Vector2d(12,-10), Math.toRadians(90))
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequence second = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(58,-10))
                .lineTo(new Vector2d(20,-12.5))
                .splineTo(new Vector2d(8,-16), Math.toRadians(-135)) //drop cone

                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(15, -10, Math.toRadians(-135)))
                .lineToLinearHeading(new Pose2d(36, -10, Math.toRadians(270)))
                //go to next cone
                .lineTo(new Vector2d(37,-30))
                //drop cone
                .waitSeconds(1)
                .splineTo(new Vector2d(5,-28), Math.toRadians(135))
                .waitSeconds(1)
                //reset
                .lineToLinearHeading(new Pose2d(12, -36, Math.toRadians(135)))

                .turn(Math.toRadians(-135))
                .build();

        TrajectorySequence third = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(58,-10))
                .lineTo(new Vector2d(20,-12.5))
                .splineTo(new Vector2d(8,-16), Math.toRadians(-135)) //drop cone

                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(15, -10, Math.toRadians(-135)))
                .lineToLinearHeading(new Pose2d(36, -10, Math.toRadians(270)))
                //go to next cone
                .lineTo(new Vector2d(37,-30))
                //drop cone
                .waitSeconds(1)
                .splineTo(new Vector2d(5,-28), Math.toRadians(135))
                .waitSeconds(1)
                //reset
                .lineToLinearHeading(new Pose2d(12, -36, Math.toRadians(135)))
                .splineTo(new Vector2d(12,-59), Math.toRadians(-90))
                .turn(Math.toRadians(-90))
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
                telemetry.addData("OpenCV", "Pos 3 Detected");
                telemetry.update();
                break;
            }
        }

        if (!isStopRequested())
            drive.followTrajectorySequence(complete);
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
    }
}
