package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.clawIntakePos;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.clawOutakePos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw implements MechanismTemplate{
    Servo clawServo;
    public Claw(HardwareMap hardwareMap, Telemetry telemetry)
    {
        setMode(hardwareMap, telemetry);
    }
    @Override
    public void setMode(HardwareMap hwmap, Telemetry telemetry) {
        clawServo = hwmap.get(Servo.class, "clawServo");
    }

    public void close(){
        clawServo.setPosition(clawIntakePos);
    }

    public void open(){
        clawServo.setPosition(clawOutakePos);
    }

    public double getPos(){
        return clawServo.getPosition();
    }
}