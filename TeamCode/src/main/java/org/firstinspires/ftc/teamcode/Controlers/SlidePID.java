package org.firstinspires.ftc.teamcode.Controlers;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SlidePID {

    private double Kp;
    private double Ki;
    private double Kd;
    public int out = 0;

    public int reference;
    public double integralSum = 0;
    public double lastError = 0;

    ElapsedTime timer = new ElapsedTime();
    public SlidePID(double p, double i, double d, int reference){
        Kp = p;
        Ki = i;
        Kd = d;
        this.reference = reference;
    }
    public void setPID(double P, double I, double D){
        Kp = P;
        Ki = I;
        Kd = D;
    }

    public void setTarget(int target){
        reference = target;
    }

    public int calculate(double slidePos){
        double error = reference - slidePos;
        double derivative = (error - lastError) / timer.seconds();
        integralSum = integralSum+(error * timer.seconds());
        out = (int)((Kp*error) + (Ki * integralSum) + (Kd*derivative));
        lastError = error;
        timer.reset();
        return out;
    }
}
