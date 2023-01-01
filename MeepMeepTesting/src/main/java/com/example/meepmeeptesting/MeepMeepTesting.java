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
                                //.splineToConstantHeading(new Vector2d(10,-58),Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(13,-58),Math.toRadians(90))
                                .splineTo(new Vector2d(13,-12), Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(26,-11, Math.toRadians(90)), Math.toRadians(0))
                                /*.addTemporalMarker(() -> {
                                    //claw.close();
                                })
                                .addSpatialMarker(new Vector2d(25,-62),() -> {
                                    //verticalSlide.setPosition(verticalSpeed, 1080);

                                })
                                .splineTo(new Vector2d(28,-7), Math.toRadians(131))
                                .waitSeconds(0.4)
                                .addTemporalMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, 890);
                                    //sleep(100);
                                    //claw.open();
                                })
                                .addSpatialMarker(new Vector2d(7, -21), () -> {
                                    //verticalSlide.setPosition(verticalSpeed,190);
                                })
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(32,-11, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(59,-15, Math.toRadians(0)))
                                //.splineToLinearHeading(new Pose2d(59,-15, Math.toRadians(0)), Math.toRadians(180))
                                //.lineToLinearHeading(new Pose2d(59,-15, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })
                                .waitSeconds(0.5)//go to pole

                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(23,-11, Math.toRadians(90)))
                                .addDisplacementMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, verticalSlide.getPosition-400);
                                    //claw.open();
                                })
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(59.5,-12, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })
                                .waitSeconds(0.5)//go to pole
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(23,-11, Math.toRadians(90)))
                                .addDisplacementMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, verticalSlide.getPosition-400);
                                    //claw.open();
                                })
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(59.5,-12, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })
                                .waitSeconds(0.5)//go to pole
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(23,-11, Math.toRadians(90)))
                                .addDisplacementMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, verticalSlide.getPosition-400);
                                    //claw.open();
                                })
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(59.5,-12, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })
                                .waitSeconds(0.5)//go to pole
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(23,-11, Math.toRadians(90)))
                                .addDisplacementMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, verticalSlide.getPosition-400);
                                    //claw.open();
                                })
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(59.5,-12, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })
                                .waitSeconds(0.5)//go to pole
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(23,-11, Math.toRadians(90)))
                                .addDisplacementMarker(() -> {
                                    //verticalSlide.setPosition(verticalSpeed, verticalSlide.getPosition-400);
                                    //claw.open();
                                })
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(59.5,-12, Math.toRadians(0)))
                                .addDisplacementMarker(() ->{
                                    //claw.close();
                                    //sleep(100);
                                    //verticalSlide.setPosition(verticalSpeed, 3130);
                                })

                                 */
                                .build()
                );



        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(bot)
                .start();
    }
}