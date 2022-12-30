package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SimpleBotVerticalSlide implements MechanismTemplate{
    DcMotor verticalMotor;
    Telemetry telemetry;
    public SimpleBotVerticalSlide(HardwareMap hwMap, Telemetry telemetry){
        setMode(hwMap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        verticalMotor = hwMap.get(DcMotor.class, "verticalSlide");
        verticalMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        verticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        verticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setPosition(double pow, int pos){
        verticalMotor.setPower(pow);
        verticalMotor.setTargetPosition(pos);
        verticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int getPosition(){
        return verticalMotor.getTargetPosition();
    }

    public void stop(){
        verticalMotor.setPower(0);
    }

}
