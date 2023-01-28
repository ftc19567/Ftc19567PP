package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controlers.SlideFeedforwardControl;
import org.firstinspires.ftc.teamcode.Controlers.SlidePID;


@TeleOp(name="SlidePIDFtest", group = "")
@Config
public class MotionProfilePIDFtest extends OpMode {
    public static double distance;
    public static double MaxAcc;
    public static double MaxVelocity;
    MotionProfilePosition position;
    MotionProfileVelocity velocity;
    MotionProfileAcceleration acceleration;
    private SlidePID controller;
    private SlideFeedforwardControl feedforward;
    public static double p=0,i=0,d=0;
    public static double Kv,Ka,Kg;
    MotionProfilePosition targetPos;
    MotionProfileVelocity targetVelocity;
    MotionProfileAcceleration targetAcc;
    public static double target;
    DcMotorEx slideMotor;

    ElapsedTime current = new ElapsedTime();
    @Override
    public void init() {
        feedforward = new SlideFeedforwardControl(Kv,Ka,Kg);
        controller = new SlidePID(p,i,d,target);
        slideMotor = hardwareMap.get(DcMotorEx.class, "verticalSlide");
    }

    @Override
    public void start() {
        current.reset();
    }

    @Override
    public void loop() {
        int slidePos = slideMotor.getCurrentPosition();
        feedforward.calculate(targetVelocity.motion_profile_velocity(MaxAcc, MaxVelocity, distance),targetAcc.motion_profile_acceleration(MaxAcc, MaxVelocity, distance));
        double pid = controller.calculate(slidePos);
    }
}
