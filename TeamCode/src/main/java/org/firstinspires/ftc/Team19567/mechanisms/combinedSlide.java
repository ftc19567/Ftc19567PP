package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class combinedSlide {
    boolean isHorizontallyExtended;
    boolean isVerticallyExtend;
    Servo horizontalServo;
    Servo verticalServo;
    Telemetry telemetry;
    public combinedSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        verticalServo = hwMap.get(Servo.class, "verticalServo");
        horizontalServo = hwMap.get(Servo.class, "horizontalServo");
        isVerticallyExtend = false;
        isHorizontallyExtended = false;
    }

    public boolean isHorizontallyExtended() {
        return isHorizontallyExtended;
    }

    public boolean isVerticallyExtend() {
        return isVerticallyExtend;
    }

    public void extendHorizontally(){
        horizontalServo.setPosition(1);
        isHorizontallyExtended = true;
    }

    public void retractHorizontally(){
        horizontalServo.setPosition(0);
        isHorizontallyExtended= false;
    }

    public void extendVertically(){
        verticalServo.setPosition(1);
        isVerticallyExtend = true;
    }

    public void retractVertically(){
        verticalServo.setPosition(0);
        isVerticallyExtend= false;
    }

    public double sethorizontalServo(double pos) {
        horizontalServo.setPosition(pos);
        return horizontalServo.getPosition();
    }

    public double setverticalServo(double pos) {
        verticalServo.setPosition(pos);
        return verticalServo.getPosition();
    }

    public void sethorizontalServoDirection(Servo.Direction direction){
        horizontalServo.setDirection(direction);
    }

    public void setverticalServoDirection(Servo.Direction direction){
        verticalServo.setDirection(direction);

    }
}
