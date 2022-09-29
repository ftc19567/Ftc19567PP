package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class horizontalSlide {
    boolean isExtended;
    DcMotor leftHorizontalMotor;
    DcMotor rightHorizontalMotor;
    Telemetry telemetry;
    public horizontalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        leftHorizontalMotor = hwMap.get(DcMotor.class, "horizontalMotor");
        leftHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftHorizontalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightHorizontalMotor = hwMap.get(DcMotor.class, "horizontalMotor");
        rightHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightHorizontalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        isExtended = false;
    }

    public void extend(){
        rightHorizontalMotor.setPower(1);
        leftHorizontalMotor.setPower(1);
    }

    public void retract(){
        rightHorizontalMotor.setPower(-1);
        leftHorizontalMotor.setPower(-1);
    }

    public void stop(){
        rightHorizontalMotor.setPower(0);
        leftHorizontalMotor.setPower(0);
    }


    public void setMotorPower(double pos){
        rightHorizontalMotor.setPower(pos);
        leftHorizontalMotor.setPower(pos);
    }
}
