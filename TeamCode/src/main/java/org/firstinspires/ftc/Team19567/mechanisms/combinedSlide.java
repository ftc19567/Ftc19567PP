package org.firstinspires.ftc.Team19567.mechanisms;


import static org.firstinspires.ftc.Team19567.util.MechanismConstants.HorizontalSpeed;
import static org.firstinspires.ftc.Team19567.util.MechanismConstants.VerticalSpeed;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class combinedSlide {
    verticalSlide combinedVerticalSlide;
    horizontalSlide combinedHorizontalSlide;
    Telemetry telemetry;
    public combinedSlide(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry = telemetry;
        combinedHorizontalSlide = new horizontalSlide(hwMap, telemetry);
        combinedVerticalSlide = new verticalSlide(hwMap, telemetry);
    }
    

    public void extendHorizontally(){
        combinedHorizontalSlide.extend(HorizontalSpeed);
    }

    public void retractHorizontally(){
        combinedHorizontalSlide.retract(HorizontalSpeed);
    }

    public void stopHorizontally(){
        combinedHorizontalSlide.stop();
    }

    public void extendVertically(){
        combinedVerticalSlide.extend(VerticalSpeed);
    }

    public void retractVertically(){
        combinedVerticalSlide.retract(VerticalSpeed);
    }

    public void stopVertically(){
        combinedVerticalSlide.stop();
    }

    public void sethorizontalMotor(double pos) {
        combinedHorizontalSlide.setMotorPower(pos);
    }

    public void setverticalMotor(double pos) {
        combinedVerticalSlide.setMotorPower(pos);
    }

}
