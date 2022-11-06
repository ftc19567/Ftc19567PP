package org.firstinspires.ftc.Team19567.mechanisms.MechanismTests;

import static org.firstinspires.ftc.Team19567.util.UtilConstants.HorizontalSpeed;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Team19567.mechanisms.horizontalSlide;
@TeleOp(name = "horizontalSlideTest", group = "Intake")
public class horizontalTestOpMode extends OpMode {
    horizontalSlide testHorizontalSlide;
    boolean aIsPressed;
    @Override
    public void init() {
        testHorizontalSlide = new horizontalSlide(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_right){
            testHorizontalSlide.extend(HorizontalSpeed);
        }
        else if(gamepad1.dpad_left){
            testHorizontalSlide.retract(HorizontalSpeed);
        }else{
            testHorizontalSlide.stop();
        }
    }
}
