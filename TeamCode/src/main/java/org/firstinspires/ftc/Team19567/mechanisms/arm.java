package org.firstinspires.ftc.Team19567.mechanisms;

import static org.firstinspires.ftc.Team19567.util.MechanismConstants.FirstArmFlipPosition;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.SecondArmFlipPosition;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class arm extends Mechanism{
    Telemetry telemetry;
    Servo armServo;
    public arm(HardwareMap hwMap, Telemetry telemetry){
        setMode(hwMap, telemetry);
    }

    @Override
    public void setMode(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        armServo = hwMap.get(Servo.class, "armServo");
        armServo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public double flip(){
        if(armServo.getPosition() == FirstArmFlipPosition){
            armServo.setPosition(SecondArmFlipPosition);
        }
        else if(armServo.getPosition() == SecondArmFlipPosition){
            armServo.setPosition(FirstArmFlipPosition);
        }
        return armServo.getPosition();
    }

    @Override
    public void setPosition(int pos){
        armServo.setPosition(pos);
    }

    @Override
    public void setDirection(Servo.Direction direction){
        armServo.setDirection(direction);
    }

}
