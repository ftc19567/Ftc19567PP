package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String args[]) {
        MeepMeep mm = new MeepMeep(800);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(mm)
                // set first bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                // set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(73.17330064499293, 73.17330064499293, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(32.5, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(48,-14.8, Math.toRadians(90)))
                                //.lineToLinearHeading(new Pose2d(29,-4, Math.toRadians(135)))
                                .splineTo(new Vector2d(35,-8), Math.toRadians(135))
                                .build()
                );



        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(bot)
                .start();
    }
}