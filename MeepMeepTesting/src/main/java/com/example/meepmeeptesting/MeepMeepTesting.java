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
                        drive.trajectorySequenceBuilder(new Pose2d(62, -38, Math.toRadians(180)))
                                .addTemporalMarker(0,() -> {
                                    //claw.close();
                                    //sleep(500);
                                    //verticalSlide.setPosition(verticalSpeed, 200);
                                })
                                .waitSeconds(2)

                                .lineTo(new Vector2d(54,15))
                                .lineTo(new Vector2d(28,15))
                                .addDisplacementMarker(() ->{
                                    //verticalSlide.setPosition(verticalSpeed, 0);
                                })
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