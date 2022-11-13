package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class horizontalSlide implements MechanismTemplate{
    //folder for constants
    //Set to position
    //Interface for everything
    DcMotor leftHorizontalMotor;
    DcMotor rightHorizontalMotor;
    Telemetry telemetry;
    public horizontalSlide(HardwareMap hwMap, Telemetry telemetry){
        setMode(hwMap, telemetry);
    }
    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftHorizontalMotor = hwMap.get(DcMotor.class, "leftHorizontalMotor");
        leftHorizontalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftHorizontalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightHorizontalMotor = hwMap.get(DcMotor.class, "rightHorizontalMotor");
        rightHorizontalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightHorizontalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void extend(double pow){
        rightHorizontalMotor.setPower(pow);
        leftHorizontalMotor.setPower(pow);
    }
    public void retract(double pow){
        rightHorizontalMotor.setPower(-pow);
        leftHorizontalMotor.setPower(-pow);
    }

    public void stop(){
        rightHorizontalMotor.setPower(0);
        leftHorizontalMotor.setPower(0);
    }

    public void setPosition(int pos) {
        rightHorizontalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftHorizontalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightHorizontalMotor.setTargetPosition(pos);
        leftHorizontalMotor.setTargetPosition(pos);
        rightHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftHorizontalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
