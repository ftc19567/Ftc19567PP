package org.firstinspires.ftc.Team19567.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {
    DcMotor frontLeftMotor = null;
    DcMotor frontRightMotor = null;
    DcMotor backLeftMotor = null;
    DcMotor backRightMotor = null;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;
    double y;
    double x;
    double rx;
    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "leftFrontLeftEnc");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rightFrontBackEnc");
        backLeftMotor = hardwareMap.get(DcMotor.class, "leftBackRightEnc");
        backRightMotor = hardwareMap.get(DcMotor.class, "rightBackNoEnc");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeftPower = (y+x+rx)/denominator;
        backLeftPower = (y-x+rx)/denominator;
        frontRightPower = (y-x-rx)/denominator;
        backRightPower = (y+x-rx)/denominator;

        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        if(gamepad1.a){
            telemetry.addData("done", "1");
        }
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
