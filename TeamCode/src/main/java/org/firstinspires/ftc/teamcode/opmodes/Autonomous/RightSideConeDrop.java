package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.topRight;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(group = "official")
public class RightSideConeDrop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = topRight; //60 45 -90

        drive.setPoseEstimate(startPose);

        TrajectorySequence toPole = drive.trajectorySequenceBuilder(startPose)
                //go to pole
                .lineTo(new Vector2d(54,10))
                .lineTo(new Vector2d(20,10))
                //drop cone
                .splineTo(new Vector2d(7,17), Math.toRadians(135))
                //go to marker pick up new cone
                .lineTo(new Vector2d(15,12))
                .lineToLinearHeading(new Pose2d(37,13.5, Math.toRadians(90)))
                //go to next cone
                .lineTo(new Vector2d(37,30))
                //drop cone
                .waitSeconds(1)
                .splineTo(new Vector2d(5,28), Math.toRadians(-135))
                //reset
                .lineToLinearHeading(new Pose2d(12, 32, Math.toRadians(-270)))
                .splineTo(new Vector2d(12,36), Math.toRadians(90))
                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toPole);
    }
}
