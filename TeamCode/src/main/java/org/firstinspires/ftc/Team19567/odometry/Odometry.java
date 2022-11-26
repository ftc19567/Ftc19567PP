package org.firstinspires.ftc.Team19567.odometry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.Team19567.util.XyhVector;

public class Odometry {
    public DcMotor EncoderRight;
    public DcMotor EncoderLeft;
    public DcMotor EncoderAux;
    public DcMotor leftFrontLeftEnc;
    public DcMotor rightFrontBackEnc;
    public DcMotor leftBackRightEnc;

    final static double L = 0;//cm
    final static double B = 0;//cm
    final static double R = 0;//wheel radius cm
    final static double N = 8192; //encoder ticks per rev
    final static double cm_per_tick = 2.0*Math.PI*R/N;

    public int currentRightPos=0;
    public int currentLeftPos=0;
    public int currentAuxPos=0;

    private int oldRightPos=0;
    private int oldLeftPos=0;
    private int oldAuxPos =0;

    public XyhVector START_POS = new XyhVector(0,0,0);
    public XyhVector pos = new XyhVector(START_POS.x, START_POS.y, START_POS.h);


    public Odometry(HardwareMap hardwareMap,DcMotor leftFrontLeftEnc, DcMotor rightFrontBackEnc,  DcMotor leftBackRightEnc)
    {
        EncoderRight = leftBackRightEnc;
        EncoderAux = rightFrontBackEnc;
        EncoderLeft = leftFrontLeftEnc;
    }

    public void odometry(){
        oldAuxPos=currentAuxPos;
        oldLeftPos=currentLeftPos;
        oldRightPos=currentRightPos;

        currentAuxPos=EncoderAux.getCurrentPosition();
        currentLeftPos=-EncoderLeft.getCurrentPosition();
        currentRightPos=-EncoderRight.getCurrentPosition();

        int dn1 = currentLeftPos-oldLeftPos;
        int dn2 = currentRightPos-oldRightPos;
        int dn3 = currentAuxPos-oldAuxPos;

        double dtheta = cm_per_tick*(dn2-dn1)/L;
        double dx = cm_per_tick * (dn1+dn2)/2.0;
        double dy = cm_per_tick * (dn3-(dn2-dn1) * B/L);

        double theta = pos.h + (dtheta/2.0);
        pos.x += dx* Math.cos(theta) - dy* Math.sin(theta);
        pos.y += dx* Math.sin(theta) + dy*Math.cos(theta);
        pos.h +=dtheta;
    }
}
