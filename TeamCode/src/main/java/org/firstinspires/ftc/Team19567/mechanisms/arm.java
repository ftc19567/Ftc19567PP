package org.firstinspires.ftc.Team19567.mechanisms;

import static org.firstinspires.ftc.Team19567.util.UtilConstants.FirstArmFlipPosition;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.SecondArmFlipPosition;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class arm implements MechanismTemplate{
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


    public double flip(){
        if(armServo.getPosition() == FirstArmFlipPosition){
            armServo.setPosition(SecondArmFlipPosition);
        }
        else if(armServo.getPosition() == SecondArmFlipPosition){
            armServo.setPosition(FirstArmFlipPosition);
        }
        return armServo.getPosition();
    }


    public void setPosition(int pos){
        armServo.setPosition(Range.clip(pos, armServo.MIN_POSITION, armServo.MAX_POSITION));
    }


    public void setDirection(Servo.Direction direction){
        armServo.setDirection(direction);
    }

}
