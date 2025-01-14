package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class UtilConstants {
    static public final double verticalSpeed = 1;

    static public final double FirstArmFlipPosition = 0;
    static public final double SecondArmFlipPosition = 0;
    static public final double ThirdArmFlipPosition = 0;

    static public final double sense = 0.55;
    static public final double slowModeSense = 0.3;
    static public final double speedModeSense = 0.8;

    public static final int tagFirstId = 0;
    public static final int tagSecondId = 1;
    public static final int tagThirdId = 2;

    public static final double clawIntakePos = 0.1;
    public static final double clawOuttakePos = 0.5 ;

    public static final int slidePos1 = 1730;
    public static final int slidePos2 = 1320;
    public static final int slidePos3 = 765;

    public static LOCATION location= LOCATION.FIRST;
    public static int tag = 0;

    public static final Pose2d right = new Pose2d(33.2, -62, Math.toRadians(90));
    public static final Pose2d left = new Pose2d(-37.3, -62, Math.toRadians(90));
}
