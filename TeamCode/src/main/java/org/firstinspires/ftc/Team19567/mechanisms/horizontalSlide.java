package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class horizontalSlide {
    boolean isExtended;
    Servo horizontalServo;
    Telemetry telemetry;
    public horizontalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        horizontalServo = hwMap.get(Servo.class, "horizontalServo");
        isExtended = false;
    }

    public void extend(){
        horizontalServo.setPosition(1);
        isExtended = true;
    }

    public void retract(){
        horizontalServo.setPosition(0);
        isExtended= false;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public double setPosition(double pos){
        horizontalServo.setPosition(pos);
        return horizontalServo.getPosition();
    }

    public void setDirection(Servo.Direction direction){
        horizontalServo.setDirection(direction);
    }
}
