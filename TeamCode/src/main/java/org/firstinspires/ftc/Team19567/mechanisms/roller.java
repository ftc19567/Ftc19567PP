package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class roller {
    Servo rollerServo;
    Telemetry telemetry;
    public roller(HardwareMap hwmap, Telemetry telemetry){
        rollerServo = hwmap.get(Servo.class, "roller Servo");
        this.telemetry = telemetry;
    }
}
