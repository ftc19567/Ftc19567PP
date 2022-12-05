package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.Team19567.odometry.Odometry;
@TeleOp(name = "TestOdometry")
public class OdometryTest extends LinearOpMode {
    DcMotor leftFrontLeftEnc;
    DcMotor leftBackRightEnc;
    DcMotor rightFrontBackEnc;
    DcMotor rightBackNoEnc;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFrontLeftEnc = hardwareMap.get(DcMotor.class, "leftFrontLeftEnc");
        rightFrontBackEnc = hardwareMap.get(DcMotor.class, "rightFrontBackEnc");
        leftBackRightEnc = hardwareMap.get(DcMotor.class, "leftBackRightEnc");
        rightBackNoEnc = hardwareMap.get(DcMotor.class, "RightBackNoEnc");
        leftFrontLeftEnc.setDirection(DcMotor.Direction.FORWARD);
        rightFrontBackEnc.setDirection(DcMotor.Direction.REVERSE);
        leftBackRightEnc.setDirection(DcMotor.Direction.FORWARD);
        rightBackNoEnc.setDirection(DcMotor.Direction.REVERSE);
        leftBackRightEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontBackEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontLeftEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Odometry testOdometry = new Odometry(hardwareMap, leftFrontLeftEnc, rightFrontBackEnc, leftBackRightEnc);
        while (opModeIsActive())
        {
            testOdometry.odometry();
            telemetry.addData("Status", "Started");
            telemetry.addData("x", testOdometry.pos.x);
            telemetry.addData("y", testOdometry.pos.y);
            telemetry.addData("h", testOdometry.pos.h);

            telemetry.update();
        }
    }
}
