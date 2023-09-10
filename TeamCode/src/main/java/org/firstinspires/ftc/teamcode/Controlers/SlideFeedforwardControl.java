package org.firstinspires.ftc.teamcode.Controlers;

public class SlideFeedforwardControl {
    public double Kv = 0;
    public double Ka = 0;
    public double Kg = 0;
    public double out = 0;
    public SlideFeedforwardControl(double velocityConstant,double acclerationConstant,double gravityConstant){
        Kv = velocityConstant;
        Ka = acclerationConstant;
        Kg = gravityConstant;
    }

   public double calculate(double TargetVelocity, double TargetAcceleration){
        out = TargetVelocity * Kv + TargetAcceleration * Ka + Kg;
        return out;
   }
}
