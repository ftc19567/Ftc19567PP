package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Team19567.mechanisms.combinedSlide;

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
        if(gamepad1.a && !aIsPressed){
            if(testCombinedSlides.isHorizontallyExtended())
            {
                testCombinedSlides.extendHorizontally();
            }
            else if(!testCombinedSlides.isHorizontallyExtended()){
                testCombinedSlides.retractHorizontally();
            }
            else{

            }
            aIsPressed = gamepad1.a;
        }
        if(gamepad1.b && !bIsPressed)
        {
            if(testCombinedSlides.isVerticallyExtend())
            {
                testCombinedSlides.extendVertically();
            }
            else if(!testCombinedSlides.isVerticallyExtend()){
                testCombinedSlides.retractVertically();
            }
            else{

            }
            bIsPressed = gamepad1.b;
        }
    }
}
