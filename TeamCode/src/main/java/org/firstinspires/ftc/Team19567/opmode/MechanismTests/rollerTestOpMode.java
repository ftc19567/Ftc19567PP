package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Team19567.mechanisms.roller;

public class rollerTestOpMode extends OpMode {
    boolean aIsPressed;
    boolean bIsPressed;
    roller testRoller;
    @Override
    public void init() {
        testRoller = new roller(hardwareMap, telemetry);
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
        bIsPressed = gamepad1.b;
        aIsPressed = gamepad1.a;
    }
}
