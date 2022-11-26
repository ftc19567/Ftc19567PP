package org.firstinspires.ftc.Team19567.mechanisms.MechanismTests;

import static org.firstinspires.ftc.Team19567.util.UtilConstants.IntakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.OutakeServoPosition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Team19567.mechanisms.roller;
@TeleOp(name = "rollerTest", group = "Intake")
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
            testRoller.intake(IntakeServoPosition);
        }
        else if(gamepad1.b && !bIsPressed){
            testRoller.outake(OutakeServoPosition);
        }
        bIsPressed = gamepad1.b;
        aIsPressed = gamepad1.a;
    }
}
