package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class verticalSlide implements MechanismTemplate{
    DcMotor leftVerticalMotor;
    DcMotor rightVerticalMotor;
    Telemetry telemetry;
    public verticalSlide(HardwareMap hwMap, Telemetry telemetry){
        setMode(hwMap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftVerticalMotor = hwMap.get(DcMotor.class, "leftVerticalMotor");
        leftVerticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftVerticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightVerticalMotor = hwMap.get(DcMotor.class, "rightVerticalMotor");
        rightVerticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightVerticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void extend(double pow){
        rightVerticalMotor.setPower(pow);
        leftVerticalMotor.setPower(pow);
    }

    public void retract(double pow){
        rightVerticalMotor.setPower(-pow);
        leftVerticalMotor.setPower(-pow);
    }


    public void stop(){
        rightVerticalMotor.setPower(0);
        leftVerticalMotor.setPower(0);
    }


    public void setPosition(int pos) {
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightVerticalMotor.setTargetPosition(pos);
        leftVerticalMotor.setTargetPosition(pos);
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
