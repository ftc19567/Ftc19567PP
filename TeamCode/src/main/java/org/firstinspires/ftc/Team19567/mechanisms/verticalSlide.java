package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class verticalSlide {
    Servo verticalServo;
    Telemetry telemetry;
    boolean isExtended;

    public verticalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        verticalServo = hwMap.get(Servo.class, "verticalServo");
        isExtended = false;
    }

    public void extend(){
        verticalServo.setPosition(1);
        isExtended = true;
    }

    public void retract(){
        verticalServo.setPosition(0);
        isExtended= false;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public double setPosition(double pos){
        verticalServo.setPosition(pos);
        return verticalServo.getPosition();
    }

    public void setDirection(Servo.Direction direction){
        verticalServo.setDirection(direction);
    }
}
