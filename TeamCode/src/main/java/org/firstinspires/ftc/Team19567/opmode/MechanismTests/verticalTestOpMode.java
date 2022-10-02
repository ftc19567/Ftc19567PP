package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import static org.firstinspires.ftc.Team19567.util.MechanismConstants.VerticalSpeed;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.Team19567.mechanisms.verticalSlide;

public class verticalTestOpMode extends OpMode {
    verticalSlide testVerticalSlide;
    boolean aIsPressed;
    @Override
    public void init() {
        testVerticalSlide = new verticalSlide(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up){
            testVerticalSlide.extend(VerticalSpeed);;
        }
        else if(gamepad1.dpad_down){
            testVerticalSlide.retract(VerticalSpeed);
        }else{
            testVerticalSlide.stop();
        }
    }
}
