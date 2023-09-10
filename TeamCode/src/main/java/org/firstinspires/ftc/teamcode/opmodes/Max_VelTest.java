package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="SlidePIDFtest", group = "")
@Config
public class Max_VelTest extends OpMode {
    DcMotorEx slideMotor;
    double acceleration;
    double velocity;
    double lastVelocity = 0;
    int targetPosition;
    ElapsedTime currentTime = new ElapsedTime();
    @Override
    public void init() {
        slideMotor = hardwareMap.get(DcMotorEx.class, "verticalSlide");
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void start() {
        currentTime.reset();
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up){
            targetPosition+=10;
        }else if(gamepad1.dpad_down){
            targetPosition-=10;
        }
        velocity = slideMotor.getVelocity();
        acceleration = (velocity-lastVelocity)/currentTime.seconds();
        telemetry.addData("velocity:", velocity);
        telemetry.addData("acceleration:", acceleration);
        currentTime.reset();
        lastVelocity = velocity;
        slideMotor.setTargetPosition(Range.clip(targetPosition, 0, 2000));
        slideMotor.setPower(1);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
