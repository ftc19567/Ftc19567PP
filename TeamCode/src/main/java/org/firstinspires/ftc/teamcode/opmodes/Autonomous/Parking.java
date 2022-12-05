package org.firstinspires.ftc.teamcode.opmodes.Autonomous;



import static org.firstinspires.ftc.teamcode.util.RoadRunnerState.DrivingToJunction;
import static org.firstinspires.ftc.teamcode.util.RoadRunnerState.DrivingToStack;
import static org.firstinspires.ftc.teamcode.util.RoadRunnerState.Preload;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagFirstId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagSecondId;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.tagThirdId;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.claw;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.ParkingLocation;
import org.firstinspires.ftc.teamcode.util.RoadRunnerState;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public class Parking extends LinearOpMode {
    RoadRunnerState state = RoadRunnerState.None;
    OpenCvCamera camera;
    AprilTagPipeline aprilTagPipeline;
    ParkingLocation location = ParkingLocation.None;

    double chosenX;
    double chosenY;
    Pose2d starPos = new Pose2d(-34, 60, Math.toRadians(270));
    private ElapsedTime parkingTimeout = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

        claw claw = new claw(hardwareMap, telemetry);

        SampleMecanumDrive chassis = new SampleMecanumDrive(hardwareMap);
        chassis.setPoseEstimate(starPos);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagPipeline = new AprilTagPipeline(telemetry);
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


        ArrayList<AprilTagDetection> curentDetections = aprilTagPipeline.getLatestDetections();
        if (curentDetections.size() == 1) {
            for (AprilTagDetection detection : curentDetections) {
                if (detection.id == tagFirstId) {
                    location = ParkingLocation.First;
                } else if (detection.id == tagSecondId) {
                    location = ParkingLocation.Second;
                } else if (detection.id == tagThirdId) {
                    location = ParkingLocation.Third;
                }
            }
        }
        state = Preload;
        waitForStart();
        parkingTimeout.reset();
        TrajectorySequence preload = chassis.trajectorySequenceBuilder(starPos)
                .forward(60)
                .turn(Math.toRadians(90))
                .addDisplacementMarker(() -> {
                    //verticalslide.setPosition()
                }).waitSeconds(1)
                .addDisplacementMarker(() -> {
                    //claw.open();
                    //verticalslide.setPosition()
                })
                .build();

        TrajectorySequence DrivingToStackSequence = chassis.trajectorySequenceBuilder(preload.end())
                .turn(Math.toRadians(180))
                .strafeRight(12)
                .forward(20)
                .addDisplacementMarker(() -> {
//                                    verticalSlide.setPosition()
                }).waitSeconds(0.5)
                .addDisplacementMarker(() -> {
//                                    claw.close
                })
                .build();
        TrajectorySequence DrivingToJunctionSequence = chassis.trajectorySequenceBuilder(DrivingToStackSequence.end())
                .setReversed(true)
                .back(20)
                .strafeLeft(12)
                .setReversed(false)
                .turn(Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    //verticalslide.setPosition()
                }).waitSeconds(1)
                .addDisplacementMarker(() -> {
                    //claw.open();
                    //verticalslide.setPosition()
                })
                .build();


        switch (state) {
            case Preload: {
                chassis.followTrajectorySequence(preload);
                state = DrivingToStack;
            }
            case DrivingToJunction: {
                chassis.followTrajectorySequence(DrivingToJunctionSequence);
                state = DrivingToStack;
            }
            case DrivingToStack: {
                chassis.followTrajectorySequence(DrivingToStackSequence);
                state = DrivingToJunction;
            }
        }
        if(parkingTimeout.seconds() >= 25)
        {
            state = RoadRunnerState.Parking;
        }

        if (state == RoadRunnerState.Parking) {
            switch (location) {
                case None: {
                    break;
                }
                case First: {
                    //forward(60) left(10)
                }
                case Second: {
                    //forward(60)
                }
                case Third: {
                    //forward(60) right(30)
                }
            }
        }


    }
}



