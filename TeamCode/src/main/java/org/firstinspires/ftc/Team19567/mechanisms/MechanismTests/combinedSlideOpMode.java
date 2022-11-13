package org.firstinspires.ftc.Team19567.mechanisms.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Team19567.mechanisms.combinedSlide;
@TeleOp(name = "combinedSlideTest", group = "Intake")
public class combinedSlideOpMode extends OpMode {
    boolean aIsPressed;
    boolean bIsPressed;
    combinedSlide testCombinedSlides;
    @Override
    public void init(){
        testCombinedSlides = new combinedSlide(hardwareMap, telemetry);
    }
    @Override
    public void loop(){
        if(gamepad1.dpad_up){
            testCombinedSlides.extendVertically();;
        }
        else if(gamepad1.dpad_down){
            testCombinedSlides.retractVertically();
        }else{
            testCombinedSlides.stopVertically();
        }
        if(gamepad1.dpad_right){
            testCombinedSlides.extendHorizontally();
        }else if(gamepad1.dpad_left){
            testCombinedSlides.retractHorizontally();
        }else{
            testCombinedSlides.stopVertically();
        }
    }
}
