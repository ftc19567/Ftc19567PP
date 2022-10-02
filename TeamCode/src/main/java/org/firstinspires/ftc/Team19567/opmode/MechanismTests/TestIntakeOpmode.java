package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import static org.firstinspires.ftc.Team19567.util.MechanismConstants.HorizontalSpeed;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.IntakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.OutakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.VerticalSpeed;

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
            testRoller.intake(IntakeServoPosition);

        }
        else if(gamepad1.b && !bIsPressed){
            testRoller.outake(OutakeServoPosition);

        }

        if(gamepad1.dpad_up){
            testVerticalSlide.extend(VerticalSpeed);;
        }
        else if(gamepad1.dpad_down){
            testVerticalSlide.retract(VerticalSpeed);
        }else{
            testVerticalSlide.stop();
        }

        if(gamepad1.dpad_right){
            testHorizontalSlide.extend(HorizontalSpeed);
        }
        else if(gamepad1.dpad_left){
            testHorizontalSlide.retract(HorizontalSpeed);
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
