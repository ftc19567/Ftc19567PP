package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class FullPath extends OpMode {
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    TrajectorySequence full;
    @Override
    public void init() {
        full = drive.trajectorySequenceBuilder(new Pose2d(-34, 60, Math.toRadians(270)))
                //Preload
                .forward(60)
                .turn(Math.toRadians(90))
                .addDisplacementMarker(()->{
                    //verticalslide.setPosition()
                }).waitSeconds(1)
                .addDisplacementMarker(()->{
                    //claw.open();
                    //verticalslide.setPosition()
                })
                //ToStack
                .turn(Math.toRadians(180))
                .strafeRight(12)
                .forward(20)
                .addDisplacementMarker(()->{
//                                    verticalSlide.setPosition()
                }).waitSeconds(0.5)
                .addDisplacementMarker(()->{
//                                    claw.close
                })
                //ToJunction
                .setReversed(true)
                .back(20)
                .strafeLeft(12)
                .setReversed(false)
                .turn(Math.toRadians(180))
                .addDisplacementMarker(()->{
                    //verticalslide.setPosition()
                }).waitSeconds(1)
                .addDisplacementMarker(()->{
                    //claw.open();
                    //verticalslide.setPosition()
                })
                .build();
    }

    @Override
    public void loop() {
        drive.followTrajectorySequence(full);
    }
}
