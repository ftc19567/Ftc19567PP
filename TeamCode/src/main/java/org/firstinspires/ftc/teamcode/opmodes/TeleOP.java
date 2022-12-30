package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.clawOuttakePos;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos1;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos2;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos3;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.util.AxisDirection;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {

    boolean slowMode;
    double sense = 0.8;

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
        telemetry.addData("Status", "Awaiting Start");
        telemetry.update();
    }

    @Override
    public void start() {
        telemetry.addData("Status", "Started");
        telemetry.update();
    } 

    @Override
    public void loop() {
        // Initialize SampleMecanumDrive
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // We want to turn off velocity control for teleop
        // Velocity control per wheel is not necessary outside of motion profiled auto
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /*r = Math.hypot(x,y);
        robotAngle = Math.atan2(-y, x) - Math.PI / 4;
        frontLeftPower = (strafeSense*r*Math.sin(robotAngle)) - (rx*turnSense);
        frontRightPower = (strafeSense*r*Math.cos(robotAngle)) +(rx*turnSense);
        backLeftPower = (strafeSense*r*Math.cos(robotAngle)) -(rx*turnSense);
        backRightPower = (strafeSense*r*Math.sin(robotAngle)) +(rx*turnSense);

        rightFrontBackEnc.setPower(frontRightPower*Sense);
        leftBackRightEnc.setPower(backLeftPower*Sense);
        leftFrontLeftEnc.setPower(frontLeftPower*Sense);
        rightBackNoEnc.setPower(backRightPower*Sense);\

        */
        if(gamepad1.back) slowMode = !slowMode;
        if(slowMode) sense = 0.4;
        else sense = 0.8;

        //Intake
        //if(gamepad1.dpad_down) intakePos = clawIntakePos;
        //else if(gamepad1.dpad_up) intakePos = clawOuttakePos;
        if(gamepad1.right_trigger > 0) claw.close();
        if(gamepad1.right_bumper) claw.open();


        if(gamepad1.dpad_up)verticalSlidePos = Range.clip(verticalSlidePos + 15,0,6000);
        if(gamepad1.dpad_down) verticalSlidePos = Range.clip(verticalSlidePos - 15,0,6000);

        if(gamepad1.b) verticalSlidePos =  0;
        if(gamepad1.a) verticalSlidePos = slidePos1;
        if(gamepad1.x) verticalSlidePos = slidePos2;
        if(gamepad1.y) verticalSlidePos = slidePos3;



        verticalSlide.setPosition(verticalSpeed, (int) verticalSlidePos);
        //claw.position(intakePos);

        drive.setWeightedDrivePower(new Pose2d(-gamepad1.left_stick_y * sense, -gamepad1.left_stick_x * sense, -gamepad1.right_stick_x * sense));
        drive.update();
        Pose2d poseEstimate = drive.getPoseEstimate();


        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());

        telemetry.addData("IntakePosition", claw.getPos());
        telemetry.addData("Position", verticalSlide.getPosition());
        telemetry.addData("Servo Port", claw.portNum());
        telemetry.update();
    }

    @Override
}
