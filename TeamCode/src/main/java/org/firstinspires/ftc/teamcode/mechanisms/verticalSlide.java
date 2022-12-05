package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

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
        leftVerticalMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightVerticalMotor = hwMap.get(DcMotor.class, "rightVerticalMotor");
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftVerticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightVerticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPosition(double pow, int pos){
        leftVerticalMotor.setPower(pow);
        rightVerticalMotor.setPower(pow);
        leftVerticalMotor.setTargetPosition(pos);
        rightVerticalMotor.setTargetPosition(pos);
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }



    public void stop(){
        rightVerticalMotor.setPower(0);
        leftVerticalMotor.setPower(0);
    }

    public void setLeftPosition(int pos) {
        leftVerticalMotor.setTargetPosition(pos);
    }

    public void setRightPosition(int pos){
        rightVerticalMotor.setTargetPosition(pos);
    }

    public int getLeftPosition(){
        return leftVerticalMotor.getTargetPosition();
    }
    public int getRightPosition(){
        return rightVerticalMotor.getTargetPosition();
    }
}
