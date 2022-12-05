package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SimpleBotVerticalSlide implements MechanismTemplate{
    DcMotor VerticalMotor;
    Telemetry telemetry;
    public SimpleBotVerticalSlide(HardwareMap hwMap, Telemetry telemetry){
        setMode(hwMap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        VerticalMotor = hwMap.get(DcMotor.class, "VerticalSlide");
        VerticalMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        VerticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        VerticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPosition(double pow, int pos){
        VerticalMotor.setPower(pow);
        VerticalMotor.setTargetPosition(pos);
        VerticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int getPosition(){
        return VerticalMotor.getTargetPosition();
    }

    public void stop(){
        VerticalMotor.setPower(0);
    }

}
