package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class roller implements MechanismTemplate{
    Servo rollerServo;
    Telemetry telemetry;
    public roller(HardwareMap hwmap, Telemetry telemetry){
        setMode(hwmap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        rollerServo = hwMap.get(Servo.class, "roller Servo");
        this.telemetry = telemetry;
    }

    public void intake(double pos){
        rollerServo.setPosition(pos);
    }

    public void outake(double pos){
        rollerServo.setPosition(pos);
    }

    public void setPosition(int pos){
        rollerServo.setPosition(Range.clip(pos, rollerServo.MIN_POSITION, rollerServo.MAX_POSITION));
    }

    public void setDirection(Servo.Direction direction){
        rollerServo.setDirection(direction);
    }
}
