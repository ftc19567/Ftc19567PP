package org.firstinspires.ftc.Team19567.drive.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Claw" , group = "Dababy")
public class clawtest extends OpMode{
    //Elasped Time
    private final ElapsedTime runtime = new ElapsedTime();
    //Hardware
    private Servo clawServo = null;
    //Variables
    private double clawPos = 0;

    boolean buttonUpIsReleased = true;

    boolean buttonDownIsReleased = true;

    @Override
    public void init() {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.update();

    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Awaiting Start");
        telemetry.update();
    }
    @Override
    public void start() {
        telemetry.addData("Status", "Started");
        telemetry.update();
        runtime.reset(); //Reset runtime
    }
    @Override
    public void loop() {
        telemetry.addData("Servo Position:", clawPos);
        //L
        if(gamepad2.dpad_down) {

            if (buttonDownIsReleased) {
                buttonDownIsReleased = false;
                clawPos += 0.005;
            }

        } else {
            buttonDownIsReleased = true;
        }
        //L
        if(gamepad2.dpad_up) {

            if (buttonDownIsReleased) {
                buttonDownIsReleased = false;
                clawPos -= 0.005;
            }

        } else {
            buttonDownIsReleased = true;
        }

        clawServo.setPosition(clawPos);
    }



}