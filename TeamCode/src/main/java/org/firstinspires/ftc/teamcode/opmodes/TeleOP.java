package org.firstinspires.ftc.teamcode.opmodes;


import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.clawOuttakePos;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos1;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos2;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos3;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.strafeSense;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.turnSense;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.util.AxisDirection;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {
    //Mechanisms
//    boolean aIsPressed;
//    boolean downIsPressed;
//    boolean upIsPressed;
//    boolean bIsPressed;

    boolean Slowmode;
    double Sense;

    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;

    double robotAngle;
    double r;
    double y;
    double x;
    double rx;

    double verticalSlidePos = 0;
    double intakePos = clawOuttakePos;

    DcMotor leftFrontLeftEnc;
    DcMotor leftBackRightEnc;
    DcMotor rightFrontBackEnc;
    DcMotor rightBackNoEnc;

    Claw claw;
    SimpleBotVerticalSlide verticalSlide;

    BNO055IMU imu;

    @Override
    public void init() {
        leftFrontLeftEnc = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontBackEnc = hardwareMap.get(DcMotor.class, "rightFront");
        leftBackRightEnc = hardwareMap.get(DcMotor.class, "leftRear");
        rightBackNoEnc = hardwareMap.get(DcMotor.class, "rightRear");

        leftFrontLeftEnc.setDirection(DcMotor.Direction.FORWARD);
        rightFrontBackEnc.setDirection(DcMotor.Direction.REVERSE);
        leftBackRightEnc.setDirection(DcMotor.Direction.FORWARD);
        rightBackNoEnc.setDirection(DcMotor.Direction.REVERSE);

        leftBackRightEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontBackEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontLeftEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        claw = new Claw(hardwareMap, telemetry);
        verticalSlide = new SimpleBotVerticalSlide(hardwareMap, telemetry);
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_Y);

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

        if(gamepad1.back)Slowmode = !Slowmode;
        if(Slowmode)Sense = 0.8;
        else Sense = 1;

        //Intake
        //if(gamepad1.dpad_down) intakePos = clawIntakePos;
        //else if(gamepad1.dpad_up) intakePos = clawOuttakePos;
        if(gamepad1.right_trigger > 0) claw.close();
        if(gamepad1.right_bumper) claw.open();


        if(gamepad1.dpad_up)verticalSlidePos = Range.clip(verticalSlidePos + gamepad1.left_trigger*10,0,6000);
        if(gamepad1.dpad_down) verticalSlidePos = Range.clip(verticalSlidePos - 15,0,6000);

        if(gamepad1.b) verticalSlidePos =  0;
        if(gamepad1.a) verticalSlidePos = slidePos1;
        if(gamepad1.x) verticalSlidePos = slidePos2;
        if(gamepad1.y) verticalSlidePos = slidePos3;

        if(gamepad1.left_trigger > 0) verticalSlidePos =  200;



        verticalSlide.setPosition(verticalSpeed, (int) verticalSlidePos);
        //claw.position(intakePos);

        telemetry.addData("IntakePosition", claw.getPos());
        telemetry.addData("x:", imu.getPosition().x);
        telemetry.addData("y:", imu.getPosition().y);
        telemetry.addData("z:", imu.getPosition().z);
        telemetry.addData("imu", imu.getAngularOrientation().firstAngle);
        telemetry.addData("Position", verticalSlide.getPosition());
        telemetry.addData("Servo Port", claw.portNum());
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
