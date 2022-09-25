package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class arm {
    Telemetry telemetry;
    Servo armServo;
    public arm(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        armServo = hwMap.get(Servo.class, "armServo");
        armServo.setDirection(Servo.Direction.FORWARD);
    }

    public double flip(){
        if(armServo.getPosition() == 1){
            armServo.setPosition(0);
        }
        else if(armServo.getPosition() == 0){
            armServo.setPosition(1);
        }
        return armServo.getPosition();
    }

    public double setPosition(double pos){
        armServo.setPosition(pos);
        return armServo.getPosition();
    }

    public void setDirection(Servo.Direction direction){
        armServo.setDirection(direction);
    }

}
