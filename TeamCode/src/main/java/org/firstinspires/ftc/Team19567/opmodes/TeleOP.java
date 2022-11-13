package org.firstinspires.ftc.Team19567.opmodes;

import static org.firstinspires.ftc.Team19567.util.UtilConstants.HorizontalSpeed;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.IntakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.OutakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.VerticalSpeed;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.strafeSense;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.turnSense;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.Team19567.mechanisms.arm;
import org.firstinspires.ftc.Team19567.mechanisms.horizontalSlide;
import org.firstinspires.ftc.Team19567.mechanisms.roller;
import org.firstinspires.ftc.Team19567.mechanisms.verticalSlide;

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {
    //Mechanisms
    boolean aIsPressed;
    boolean bIsPressed;
    boolean xisPressed;
    horizontalSlide HorizontalSlide;
    arm Arm;
    verticalSlide VerticalSlide;
    roller Roller;
    //Drive
    DcMotor frontLeftMotor = null;
    DcMotor frontRightMotor = null;
    DcMotor backLeftMotor = null;
    DcMotor backRightMotor = null;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;

    double robotAngle;
    double r;
    double y;
    double x;
    double rx;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "leftFrontLeftEnc");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rightFrontBackEnc");
        backLeftMotor = hardwareMap.get(DcMotor.class, "leftBackRightEnc");
        backRightMotor = hardwareMap.get(DcMotor.class, "rightBackNoEnc");
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        Roller = new roller(hardwareMap, telemetry);
        VerticalSlide = new verticalSlide(hardwareMap, telemetry);
        HorizontalSlide = new horizontalSlide(hardwareMap, telemetry);
        Arm = new arm(hardwareMap,telemetry);
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
        x = gamepad1.left_stick_x * strafeSense;
        rx = gamepad1.right_stick_x;
//        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//        frontLeftPower = (y+x+rx)/denominator;
//        backLeftPower = (y-x+rx)/denominator;
//        frontRightPower = (y-x-rx)/denominator;
//        backRightPower = (y+x-rx)/denominator;

        r = Math.hypot(x,y);
        robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        frontLeftPower = strafeSense*r*Math.cos(robotAngle) + rx*turnSense;
        backLeftPower = strafeSense*r*Math.sin(robotAngle) +rx*turnSense;
        frontRightPower = strafeSense*r*Math.sin(robotAngle) -rx*turnSense;
        backRightPower = strafeSense*r*Math.cos(robotAngle) -rx*turnSense;

        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        //Intakes and Slides
        if(gamepad1.a && !aIsPressed) {Roller.intake(IntakeServoPosition);}
        else if(gamepad1.b && !bIsPressed){Roller.outake(OutakeServoPosition);}
        if(gamepad1.dpad_up){VerticalSlide.extend(VerticalSpeed);;}
        else if(gamepad1.dpad_down){VerticalSlide.retract(VerticalSpeed);}
        else{VerticalSlide.stop();}
        if(gamepad1.dpad_right){HorizontalSlide.extend(HorizontalSpeed);} else if(gamepad1.dpad_left){HorizontalSlide.retract(HorizontalSpeed);}
        else{HorizontalSlide.stop();}
        if(gamepad1.x && !xisPressed){Arm.flip();}

        aIsPressed = gamepad1.a;
        bIsPressed = gamepad1.b;
        xisPressed = gamepad1.x;
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
