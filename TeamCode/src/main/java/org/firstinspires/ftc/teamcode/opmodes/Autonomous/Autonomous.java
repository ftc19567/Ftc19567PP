/*package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import static org.firstinspires.ftc.teamcode.util.UtilConstants.TickPerInch;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Autonomous{
    int TargetPosition;
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftRear;
    DcMotor rightRear;
    public Autonomous(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        TargetPosition = 0;
    }

    public int getPosition()
    {
         return (leftFront.getCurrentPosition()+rightFront.getCurrentPosition()+leftRear.getCurrentPosition()+rightRear.getCurrentPosition())/4;
    }

    public void drive(int Inches, int Power){
        TargetPosition+= Inches*TickPerInch;
        leftFront.setPower(Power);
        rightFront.setPower(Power);
        leftRear.setPower(Power);
        rightRear.setPower(Power);
        leftFront.setTargetPosition(TargetPosition);
        rightFront.setTargetPosition(TargetPosition);
        leftRear.setTargetPosition(TargetPosition);
        rightRear.setTargetPosition(TargetPosition);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void strafe(int Inches, int Power){
        TargetPosition+= Inches*TickPerInch;
        leftFront.setPower(Power);
        rightFront.setPower(Power);
        leftRear.setPower(Power);
        rightRear.setPower(Power);
        leftFront.setTargetPosition(TargetPosition);
        rightFront.setTargetPosition(-TargetPosition);
        leftRear.setTargetPosition(TargetPosition);
        rightRear.setTargetPosition(-TargetPosition);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public boolean isBusy(){
        if(leftFront.isBusy() || rightFront.isBusy() || leftRear.isBusy() || rightRear.isBusy())
        {
            return true;
        }
        else{
            return false;
        }
    }
}


 */