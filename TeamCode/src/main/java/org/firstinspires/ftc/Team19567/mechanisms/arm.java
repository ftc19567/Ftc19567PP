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
    }
}
