package org.firstinspires.ftc.Team19567.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Mechanism {
    public void setMode(HardwareMap hwMap, Telemetry telemetry){};
    public void extend(double pow){};
    public void retract(double pow){};
    public void stop(){};
    public void setMotorPower(double pow){};
    public void setPosition(int pos){};
    public void setDirection(Servo.Direction direction){};
    public double flip(){return 0;};
    public void intake(double pos){};
    public void outake(double pos){};
}
