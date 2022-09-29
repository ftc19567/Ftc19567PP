package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class verticalSlide {
    DcMotor leftVerticalMotor;
    DcMotor rightVerticalMotor;
    Telemetry telemetry;
    boolean isExtended;

    public verticalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        leftVerticalMotor = hwMap.get(DcMotor.class, "leftVerticalMotor");
        leftVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftVerticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightVerticalMotor = hwMap.get(DcMotor.class, "rightVerticalMotor");
        rightVerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightVerticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        isExtended = false;
    }

    public void extend(){
        leftVerticalMotor.setPower(1);
        rightVerticalMotor.setPower(1);
    }

    public void retract(){
        leftVerticalMotor.setPower(-1);
        rightVerticalMotor.setPower(-1);
    }

    public void stop(){
        leftVerticalMotor.setPower(0);
        rightVerticalMotor.setPower(0);
    }


    public void setMotorPower(double pos){
        leftVerticalMotor.setPower(pos);
        rightVerticalMotor.setPower(pos);
    }
}
