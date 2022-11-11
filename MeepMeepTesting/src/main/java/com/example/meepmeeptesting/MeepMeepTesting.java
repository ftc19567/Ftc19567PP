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
        RoadRunnerBotEntity bot = new DefaultBotBuilder(mm)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 60, Math.toRadians(-90)))
                                .strafeLeft(23)
                                .lineTo(new Vector2d(-12, 12))
                                .strafeRight(11)
                                .addDisplacementMarker(()->{
                                    //verticalSlide.extend()
                                }).waitSeconds(2)
                                //verticalSlide.stop()
                                .addDisplacementMarker(()->{
                                    //roller.outake
                                })
                                .addDisplacementMarker(()->{
                                    //verticalSlide.retract()
                                }).waitSeconds(2)
                                .addDisplacementMarker(()->{
                                    //verticalSlide.stop()
                                })
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .addDisplacementMarker(()->{
                                    //roller.intake
                                })
                                .lineToSplineHeading(new Pose2d(-23, 12, Math.toRadians(270)))
                                .addDisplacementMarker(()->{
                                    //verticalSlide.extend()
                                }).waitSeconds(2)
                                //verticalSlide.stop()
                                .addDisplacementMarker(()->{
                                    //roller.outake
                                })
                                .addDisplacementMarker(()->{
                                    //verticalSlide.retract()
                                }).waitSeconds(2)
                                .addDisplacementMarker(()->{
                                    //verticalSlide.stop()
                                })
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .addDisplacementMarker(()->{
                                    //roller.intake
                                })
                                .lineToSplineHeading(new Pose2d(-23, 12, Math.toRadians(270)))
                                .addDisplacementMarker(()->{
                                    //verticalSlide.extend()
                                }).waitSeconds(2)
                                //verticalSlide.stop()
                                .addDisplacementMarker(()->{
                                    //roller.outake
                                })
                                .addDisplacementMarker(()->{
                                    //verticalSlide.retract()
                                }).waitSeconds(2)
                                .addDisplacementMarker(()->{
                                    //verticalSlide.stop()
                                })
                                .build()
                );

        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                // add both of the declared bot entities
                .addEntity(bot)
                .start();
    }
}