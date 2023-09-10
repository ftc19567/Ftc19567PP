package org.firstinspires.ftc.simplerobotcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.simplerobotcode.drive.util.XyhVector;

public class Odometry {
    public DcMotor EncoderRight;
    public DcMotor EncoderLeft;
    public DcMotor EncoderAux;
    public DcMotor leftFrontLeftEnc;
    public DcMotor rightFrontBackEnc;
    public DcMotor leftBackRightEnc;

    final static double L = 17.78;//cm
    final static double B = 16.51;//cm
    final static double R = 1.75;//wheel radius cm
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


    public Odometry(HardwareMap hardwareMap, DcMotor leftFrontLeftEnc, DcMotor rightFrontBackEnc, DcMotor leftBackRightEnc)
    {
        leftFrontLeftEnc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontLeftEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontBackEnc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontBackEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackRightEnc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackRightEnc.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        EncoderRight = leftBackRightEnc;
        EncoderAux = rightFrontBackEnc;
        EncoderLeft = leftFrontLeftEnc;
        XyhVector pos = new XyhVector(START_POS.x, START_POS.y, START_POS.h);

    }

    public void odometry(){
        currentAuxPos=EncoderAux.getCurrentPosition();
        currentLeftPos=-EncoderLeft.getCurrentPosition();
        currentRightPos=-EncoderRight.getCurrentPosition();

        double delta_left_encoder_pos = currentLeftPos - oldLeftPos;
        double delta_right_encoder_pos = currentRightPos - oldRightPos;
        double delta_middle_pos = currentAuxPos - oldAuxPos;

        double phi = (delta_left_encoder_pos - delta_right_encoder_pos) / L;
        delta_middle_pos = (delta_left_encoder_pos + delta_right_encoder_pos) / 2;
        double delta_perp_pos = delta_middle_pos - B * phi;

        double delta_x = delta_middle_pos * Math.cos(pos.h) - delta_perp_pos * Math.sin(pos.h);
        double delta_y = delta_middle_pos * Math.sin(pos.h) + delta_perp_pos * Math.cos(pos.h);

        pos.x += delta_x;
        pos.y += delta_y;
        pos.h += phi;

        oldAuxPos = currentAuxPos;
        oldLeftPos= currentLeftPos;
        oldRightPos = currentRightPos;
//        oldAuxPos=currentAuxPos;
//        oldLeftPos=currentLeftPos;
//        oldRightPos=currentRightPos;
//
//        currentAuxPos=EncoderAux.getCurrentPosition();
//        currentLeftPos=-EncoderLeft.getCurrentPosition();
//        currentRightPos=-EncoderRight.getCurrentPosition();
//
//        int dn1 = currentLeftPos-oldLeftPos;
//        int dn2 = currentRightPos-oldRightPos;
//        int dn3 = currentAuxPos-oldAuxPos;
//
//        double dtheta = cm_per_tick*(dn2-dn1)/L;
//        double dx = cm_per_tick * (dn1+dn2)/2.0;
//        double dy = cm_per_tick * (dn3-(dn2-dn1) * B/L);
//
//        double theta = pos.h + (dtheta/2.0);
//        pos.x += dx* Math.cos(theta) - dy* Math.sin(theta);
//        pos.y += dx* Math.sin(theta) + dy*Math.cos(theta);
//        pos.h +=dtheta;
    }
}
