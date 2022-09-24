package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class verticalSlide {
    Servo verticalServo;
    Telemetry telemetry;
    public verticalSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        verticalServo = hwMap.get(Servo.class, "verticalServo");
    }
}
