package org.firstinspires.ftc.teamcode.opmodes.Autonomous;


import static org.firstinspires.ftc.teamcode.util.UtilConstants.VerticalSpeed;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagFirstId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagSecondId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagThirdId;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.mechanisms.verticalSlide;
import org.firstinspires.ftc.teamcode.opmodes.Autonomous.AprilTagPipeline;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.ParkingLocation;
import org.firstinspires.ftc.teamcode.util.RoadRunnerState;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(group = "official")
public class RightSideAuto extends LinearOpMode{



    private LOCATION location = LOCATION.FIRST;

    private TrajectorySequence complete;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(60, 45, Math.toRadians(-90));


        drive.setPoseEstimate(startPose);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
        AprilTagPipeline aprilTagPipeline = new AprilTagPipeline(telemetry);
        camera.setPipeline(aprilTagPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });


        ArrayList<AprilTagDetection> currentDetections = aprilTagPipeline.getLatestDetections();
        if (currentDetections.size() == 1) {
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == tagFirstId) {
                    location = LOCATION.FIRST;
                } else if (detection.id == tagSecondId) {
                    location = LOCATION.SECOND;
                } else if (detection.id == tagThirdId) {
                    location = LOCATION.THIRD;
                }
            }
        }
        TrajectorySequence first = drive.trajectorySequenceBuilder(startPose)
                .strafeLeft(20)
                .forward(30)
                .build();

        TrajectorySequence second = drive.trajectorySequenceBuilder(startPose)
                .strafeRight(5)
                .forward(30)
                .build();

        TrajectorySequence third = drive.trajectorySequenceBuilder(startPose)
                .strafeRight(28)
                .forward(30)
                .build();

        switch(location) {

            case FIRST: {
                complete = first;
                telemetry.addData("OpenCV","Pos 1 Detected");
                telemetry.update();
                break;
            }

            case SECOND: {
                complete = second;
                telemetry.addData("OpenCV","Pos 2 Detected");
                telemetry.update();
                break;
            }
            case THIRD: {
                complete = third;
                telemetry.addData("OpenCV","Pos 3 Detected");
                telemetry.update();
                break;
            }
            //Code to run by default (failsafe)
            default: {
                telemetry.addData("OpenCV","Defaulted to Pos 1");
                telemetry.update();
            }
        }


        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(third);
    }
}
