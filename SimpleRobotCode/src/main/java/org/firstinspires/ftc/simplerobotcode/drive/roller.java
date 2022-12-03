package org.firstinspires.ftc.simplerobotcode.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class roller implements MechanismTemplate{
    Servo clawServo;
    Telemetry telemetry;
    public roller(HardwareMap hwmap, Telemetry telemetry){
        setMode(hwmap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        clawServo = hwMap.get(Servo.class, "intakeServo");
        this.telemetry = telemetry;
    }

    public void intake(double pos){
        clawServo.setPosition(pos);
    }

    public void outake(double pos){
        clawServo.setPosition(pos);
    }

    public void setPosition(int pos){
        clawServo.setPosition(Range.clip(pos, clawServo.MIN_POSITION, clawServo.MAX_POSITION));
    }

    public double getPosition(){
        return clawServo.getPosition();
    }

    public void setDirection(Servo.Direction direction){
        clawServo.setDirection(direction);
    }
}
