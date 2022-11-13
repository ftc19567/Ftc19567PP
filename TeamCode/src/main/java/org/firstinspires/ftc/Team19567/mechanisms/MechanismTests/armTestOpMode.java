package org.firstinspires.ftc.Team19567.mechanisms.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.Team19567.mechanisms.arm;
@TeleOp(name = "armTest", group = "Intake")
public class armTestOpMode extends OpMode {
    boolean xisPressed;
    arm testArm;
    @Override
    public void init(){
        testArm = new arm(hardwareMap,telemetry);;
    }
    @Override
    public void loop(){
        if(gamepad1.x && !xisPressed){
            testArm.flip();
        }
        xisPressed = gamepad1.x;

    }
}
