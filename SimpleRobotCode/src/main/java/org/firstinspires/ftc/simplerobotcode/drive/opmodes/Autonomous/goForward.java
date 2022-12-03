package org.firstinspires.ftc.simplerobotcode.drive.opmodes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class goForward extends OpMode {
    public DcMotor leftFrontLeftEnc;
    public DcMotor rightFrontBackEnc;
    public DcMotor leftBackRightEnc;
    public DcMotor rightBackNoEnc;

    public ElapsedTime time = new ElapsedTime();
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
    }

    @Override
    public void loop() {

    }
}
