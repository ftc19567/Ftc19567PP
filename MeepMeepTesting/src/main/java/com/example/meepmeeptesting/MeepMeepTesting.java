package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String args[]) {
        MeepMeep mm = new MeepMeep(800);

        // Declare first bot
        RoadRunnerBotEntity myFirstbot = new DefaultBotBuilder(mm)
                // set first bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                // set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(52.48291908330528, 52.48291908330528, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(62, 33, Math.toRadians(180)))
                                //go to pole
                                .lineTo(new Vector2d(54,10))
                                .lineTo(new Vector2d(20,12.5))
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
                                .build()
                );



        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                // add both of the declared bot entities
                .addEntity(myFirstbot)
                .start();
    }
}