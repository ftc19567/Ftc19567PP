package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class combinedSlide {
    Servo horizontalServo;
    Servo verticalServo;
    Telemetry telemetry;
    public combinedSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        verticalServo = hwMap.get(Servo.class, "verticalServo");
        horizontalServo = hwMap.get(Servo.class, "horizontalServo");
    }
}
