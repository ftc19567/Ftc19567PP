package org.firstinspires.ftc.teamcode.Controlers;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SlidePID {

    private double Kp = 0;
    private double Ki = 0;
    private double Kd = 0;
    public double out = 0;

    public double reference;
    public double integralSum = 0;
    public double lastError = 0;

    ElapsedTime timer = new ElapsedTime();
    public SlidePID(double p, double i, double d, double reference){
        Kp = p;
        Ki = i;
        Kd = d;
        this.reference = reference;
    }

    public double calculate(double slidePos){
        double error = reference - slidePos;
        double derivative = (error - lastError) / timer.seconds();
        integralSum = integralSum+(error * timer.seconds());
        out = (Kp*error) + (Ki * integralSum) + (Kd*derivative);
        lastError = error;
        timer.reset();
        return out;
    }
}
