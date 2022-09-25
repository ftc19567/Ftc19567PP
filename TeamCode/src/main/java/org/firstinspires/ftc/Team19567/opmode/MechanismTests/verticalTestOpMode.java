package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

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
        if(gamepad1.a && !aIsPressed){
            if(!testVerticalSlide.isExtended())
            {
                testVerticalSlide.extend();
            }
            else if(!testVerticalSlide.isExtended()){
                testVerticalSlide.retract();
            }
            else{

            }
            aIsPressed = gamepad1.a;
        }
    }
}
