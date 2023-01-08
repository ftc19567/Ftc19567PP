package org.firstinspires.ftc.teamcode.opmodes;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slowModeSense;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.speedModeSense;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.verticalSpeed;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.clawOuttakePos;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos1;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos2;
import static org.firstinspires.ftc.teamcode.util.UtilConstants.slidePos3;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.teamcode.drive.MecanumDriveCancelable;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.SimpleBotVerticalSlide;
import org.firstinspires.ftc.teamcode.util.AxisDirection;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;
import org.firstinspires.ftc.teamcode.util.UtilConstants;

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {
    //bozo

    @Override
    public void init(){
        //official
    }

    @Override
    public void loop(){
        //TeleOP
    }
    @Override
    public void stop(){
        //stop looking at our code
    }

    public void tier119567(){
        telemetry.addData("looking" , "why you looking");
    }
}
