package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class horizontalSlide {
    Servo horizontalServo;
    Telemetry telemetry;
    public horizontalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        horizontalServo = hwMap.get(Servo.class, "horizontalServo");
    }
}
