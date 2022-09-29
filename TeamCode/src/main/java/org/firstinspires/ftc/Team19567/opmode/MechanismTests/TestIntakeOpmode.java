package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Team19567.mechanisms.arm;
import org.firstinspires.ftc.Team19567.mechanisms.horizontalSlide;
import org.firstinspires.ftc.Team19567.mechanisms.roller;
import org.firstinspires.ftc.Team19567.mechanisms.verticalSlide;

public class TestIntakeOpmode extends OpMode {
    boolean aIsPressed;
    boolean bIsPressed;
    boolean xisPressed;
    horizontalSlide testHorizontalSlide;
    arm testArm;
    verticalSlide testVerticalSlide;
    roller testRoller;
    @Override
    public void init() {
        testRoller = new roller(hardwareMap, telemetry);
        testVerticalSlide = new verticalSlide(hardwareMap, telemetry);
        testHorizontalSlide = new horizontalSlide(hardwareMap, telemetry);
        testArm = new arm(hardwareMap,telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.a && !aIsPressed)
        {
            testRoller.intake();

        }
        else if(gamepad1.b && !bIsPressed){
            testRoller.outake();

        }

        if(gamepad1.dpad_up){
            testVerticalSlide.extend();;
        }
        else if(gamepad1.dpad_down){
            testVerticalSlide.retract();
        }else{
            testVerticalSlide.stop();
        }

        if(gamepad1.dpad_right){
            testHorizontalSlide.extend();
        }
        else if(gamepad1.dpad_left){
            testHorizontalSlide.retract();
        }else{
            testHorizontalSlide.stop();
        }

        if(gamepad1.x && !xisPressed){
            testArm.flip();
        }

        aIsPressed = gamepad1.a;
        bIsPressed = gamepad1.b;
        xisPressed = gamepad1.x;
    }

}
