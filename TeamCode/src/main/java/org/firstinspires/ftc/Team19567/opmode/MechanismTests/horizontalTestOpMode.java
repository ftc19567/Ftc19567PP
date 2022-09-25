package org.firstinspires.ftc.Team19567.opmode.MechanismTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Team19567.mechanisms.horizontalSlide;

public class horizontalTestOpMode extends OpMode {
    horizontalSlide testHorizontalSlide;
    boolean aIsPressed;
    @Override
    public void init() {
        testHorizontalSlide = new horizontalSlide(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.a && !aIsPressed){
            if(!testHorizontalSlide.isExtended())
            {
                testHorizontalSlide.extend();
            }
            else if(!testHorizontalSlide.isExtended()){
                testHorizontalSlide.retract();
            }
            else{

            }
            aIsPressed = gamepad1.a;
        }
    }
}
