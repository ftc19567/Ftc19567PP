package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.pipelines.LOCATION;

public class UtilConstants {
    static public final double HorizontalSpeed = 1;
    static public final double VerticalSpeed = 1;
    static public final double FirstArmFlipPosition = 1; //to outtake from back
    static public final double SecondArmFlipPosition = 0.4; //to set back to default
    static public final double ThirdArmFlipPosition = 0.6; //to outtake out from front
    static public final double IntakeServoPosition = 0;
    static public final double OutakeServoPosition = 0.43;
    static public final double strafeSense = 0.6;
    static public final double turnSense = 0.6;
    public static final int tagFirstId = 0;
    public static final int tagSecondId = 1;
    public static final int tagThirdId = 2;
    public static final double clawIntakePos = 0.4;
    public static final double clawOutakePos=0.67;

    public static LOCATION location= LOCATION.FIRST;
    public static int tag = 0;

    public static final Pose2d topRight = new Pose2d(62, 36, Math.toRadians(180));
    public static final Pose2d bottomLeft = new Pose2d(62,-36, Math.toRadians(180));
}
