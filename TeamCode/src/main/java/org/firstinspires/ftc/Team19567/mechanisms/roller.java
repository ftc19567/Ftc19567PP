package org.firstinspires.ftc.Team19567.mechanisms;

import static org.firstinspires.ftc.Team19567.util.MechanismConstants.IntakeServoPosition;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.OutakeServoPosition;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class roller extends Mechanism{
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
    @Override
    public void intake(double pos){
        rollerServo.setPosition(pos);
    }
    @Override
    public void outake(double pos){
        rollerServo.setPosition(pos);
    }
    @Override
    public void setPosition(int pos){
        rollerServo.setPosition(pos);
    }
    @Override
    public void setDirection(Servo.Direction direction){
        rollerServo.setDirection(direction);
    }
}
