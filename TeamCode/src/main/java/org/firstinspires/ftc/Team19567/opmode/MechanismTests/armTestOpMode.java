package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.Team19567.mechanisms.arm;

public class armTestOpMode extends OpMode {
    boolean aisPressed = false;
    arm testArm;
    @Override
    public void init(){
        testArm = new arm(hardwareMap,telemetry);;
    }
    @Override
    public void loop(){
        if(gamepad1.a && !aisPressed){
            testArm.flip();
        }
        aisPressed = gamepad1.a;

    }
}
