package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.pipelines.LOCATION;

public class UtilConstants {
    static public final double verticalSpeed = 1;
    static public final double HorizontalSpeed = 0.8;

    static public final double FirstArmFlipPosition = 0;
    static public final double SecondArmFlipPosition = 0;
    static public final double ThirdArmFlipPosition = 0;

    static public final double strafeSense = 0.6;
    static public final double turnSense = 0.6;

    public static final int tagFirstId = 0;
    public static final int tagSecondId = 1;
    public static final int tagThirdId = 2;

    public static final double clawIntakePos = 0;
    public static final double clawOuttakePos = 1;

    public static final int slidePos1 = 3470;
    public static final int slidePos2 = 2670;
    public static final int slidePos3 = 1845;


    public static LOCATION location= LOCATION.FIRST;
    public static int tag = 0;

    public static final Pose2d topRight = new Pose2d(57, 32.5, Math.toRadians(180));
    public static final Pose2d bottomLeft = new Pose2d(62, -38, Math.toRadians(180));
}
