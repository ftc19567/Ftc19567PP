package org.firstinspires.ftc.simplerobotcode.drive.opmodes;

import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.IntakeServoPosition;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.OutakeServoPosition;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.VerticalSpeed;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.strafeSense;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.turnSense;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.IntakeServoPosition;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.OutakeServoPosition;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.VerticalSpeed;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.strafeSense;
import static org.firstinspires.ftc.simplerobotcode.drive.util.UtilConstants.turnSense;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.simplerobotcode.drive.roller;
import org.firstinspires.ftc.simplerobotcode.drive.verticalSlide;

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {
    //mechanisms
//    boolean aIsPressed;
//    boolean downIsPressed;
//    boolean upIsPressed;
//    boolean bIsPressed;
    boolean Slowmode;
    double Sense;
    verticalSlide VerticalSlide;
    roller Roller;
    //Drive
//    DcMotor frontLeftMotor = null;
//    DcMotor frontRightMotor = null;
//    DcMotor backLeftMotor = null;
//    DcMotor backRightMotor = null;
    DcMotor leftFrontLeftEnc;
    DcMotor leftBackRightEnc;
    DcMotor rightFrontBackEnc;
    DcMotor rightBackNoEnc;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;
    double verticalSlidePosition = 0;
    double robotAngle;
    double r;
    double y;
    double x;
    double rx;

    Boolean rightBumperIsPressed;
    @Override
    public void init() {
        leftFrontLeftEnc = hardwareMap.get(DcMotor.class, "leftFrontLeftEnc");
        rightFrontBackEnc = hardwareMap.get(DcMotor.class, "rightFrontBackEnc");
        leftBackRightEnc = hardwareMap.get(DcMotor.class, "leftBackRightEnc");
        rightBackNoEnc = hardwareMap.get(DcMotor.class, "RightBackNoEnc");
        leftFrontLeftEnc.setDirection(DcMotor.Direction.FORWARD);
        rightFrontBackEnc.setDirection(DcMotor.Direction.REVERSE);
        leftBackRightEnc.setDirection(DcMotor.Direction.FORWARD);
        rightBackNoEnc.setDirection(DcMotor.Direction.REVERSE);
        Roller = new roller(hardwareMap, telemetry);
        VerticalSlide = new verticalSlide(hardwareMap, telemetry);
//        HorizontalSlide = new horizontalSlide(hardwareMap, telemetry);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        y = gamepad1.left_stick_y;
        x = -gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;
//        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//        frontLeftPower = (y+x+rx)/denominator;
//        backLeftPower = (y-x+rx)/denominator;
//        frontRightPower = (y-x-rx)/denominator;
//        backRightPower = (y+x-rx)/denominator;

        r = Math.hypot(x,y);
        robotAngle = Math.atan2(-y, x) - Math.PI / 4;
        frontLeftPower = (strafeSense*r*Math.sin(robotAngle)) - (rx*turnSense);
        frontRightPower = (strafeSense*r*Math.cos(robotAngle)) +(rx*turnSense);
        backLeftPower = (strafeSense*r*Math.cos(robotAngle)) -(rx*turnSense);
        backRightPower = (strafeSense*r*Math.sin(robotAngle)) +(rx*turnSense);

        rightFrontBackEnc.setPower(frontRightPower*Sense);
        leftBackRightEnc.setPower(backLeftPower*Sense);
        leftFrontLeftEnc.setPower(frontLeftPower*Sense);
        rightBackNoEnc.setPower(backRightPower*Sense);
        //Intakes and Slides
        if(gamepad1.right_trigger>0 || gamepad2.right_trigger>0) {Roller.intake(IntakeServoPosition);}
        else if(gamepad1.right_bumper || gamepad2.right_bumper) {Roller.intake(OutakeServoPosition);}
        if(gamepad1.left_trigger>0){verticalSlidePosition+=gamepad1.left_trigger*5;}//when tested the slides couldnt go down
        if(gamepad2.left_trigger>0){verticalSlidePosition+=gamepad2.left_trigger*5;}//and the manual setting was way to high, one tap would go all the way up
        if(gamepad1.left_bumper || gamepad2.left_bumper) {verticalSlidePosition = Range.clip(verticalSlidePosition-10, 0.0, verticalSlidePosition-5);}
//        if(gamepad1.dpad_right){HorizontalSlide.extend(HorizontalSpeed);} else if(gamepad1.dpad_left){HorizontalSlide.retract(HorizontalSpeed);}
//        else{HorizontalSlide.stop();}
        if(gamepad1.x){Slowmode = !Slowmode;}
        if(Slowmode){Sense = 0.5;}
        else Sense = 1;
        if(gamepad1.b||gamepad2.b)verticalSlidePosition=235;
        if(gamepad1.a||gamepad2.a)verticalSlidePosition=2753;
//        bIsPressed = gamepad1.b;
//        aIsPressed = gamepad1.a;
//        downIsPressed = gamepad1.dpad_down;
//        upIsPressed = gamepad1.dpad_up;
        rightBumperIsPressed = gamepad1.right_bumper;
        VerticalSlide.setPosition(VerticalSpeed, (int) verticalSlidePosition);
        telemetry.addData("IntakePosition", Roller.getPosition());
        telemetry.addData("Left", VerticalSlide.getLeftPosition());
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
