package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controlers.SlideFeedforwardControl;
import org.firstinspires.ftc.teamcode.Controlers.SlidePID;


@TeleOp(name="SlidePIDFtest", group = "")
@Config
public class MotionProfilePIDFtest extends OpMode {
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    public static double distance;
//    public static double MaxAcc;
//    public static double MaxVelocity;
    private SlidePID controller;
//    private SlideFeedforwardControl feedforward;
    public static double p=0,i=0,d=0;
    public static double Kv,Ka,Kg;
    MotionProfilePosition targetPos;
//    MotionProfileVelocity targetVelocity;
//    MotionProfileAcceleration targetAcc;
    public static int target;
    private int slidePos;
    DcMotorEx slideMotor;

    ElapsedTime current = new ElapsedTime();
    @Override
    public void init() {
//        feedforward = new SlideFeedforwardControl(Kv,Ka,Kg);
        telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());
        controller = new SlidePID(p,i,d,target);
        slideMotor = hardwareMap.get(DcMotorEx.class, "verticalSlide");
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void start() {
        current.reset();
    }

    @Override
    public void loop() {
        controller.setTarget(target);
        controller.setPID(p,i,d);
        slidePos = slideMotor.getCurrentPosition();
//        feedforward.calculate(targetVelocity.motion_profile_velocity(MaxAcc, MaxVelocity, distance),targetAcc.motion_profile_acceleration(MaxAcc, MaxVelocity, distance));
        int pid = controller.calculate(slidePos);
        telemetry.addData("slidePos", slidePos);
        telemetry.addData("PID:", pid);
        telemetry.addData("target", target);
        slideMotor.setTargetPosition(Range.clip(pid,0,2000));
        slideMotor.setPower(1);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
