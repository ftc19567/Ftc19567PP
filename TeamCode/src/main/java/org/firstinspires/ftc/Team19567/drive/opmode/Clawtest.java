package org.firstinspires.ftc.Team19567.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "Claw" , group = "Dababy")
public class Clawtest extends OpMode{
    //Elasped Time
    private final ElapsedTime runtime = new ElapsedTime();
    //Hardware
    private Servo ClawServo = null;
    //Variables
    private double ClawPos = 0.5;

    @Override
    public void init() {
        ClawServo = hardwareMap.get(Servo.class, "ClawServo");
        telemetry.update();

    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Awaiting Start");
        telemetry.update();
    }
    @Override
    public void start() {
        telemetry.addData("Status", "Started");
        telemetry.update();
        runtime.reset(); //Reset runtime
    }
    @Override
    public void loop() {
        if(gamepad2.right_bumper) ClawPos = 0.64;
        else if(gamepad2.dpad_down) ClawPos = 0.38;
        telemetry.addData("Servo Position:", ClawPos);
    }



}
