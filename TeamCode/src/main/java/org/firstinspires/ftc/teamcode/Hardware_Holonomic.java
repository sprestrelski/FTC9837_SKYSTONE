package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sam on 7/10/2019.
 */

public class Hardware_Holonomic {

    //Drive train:
    DcMotor rightRearDrive;
    DcMotor leftRearDrive;
    DcMotor rightFrontDrive;
    DcMotor leftFrontDrive;
    public void init(HardwareMap hwMap){
        //set direction of motors facing opposite directions
        rightRearDrive = hwMap.dcMotor.get("rightRearDrive");
        rightFrontDrive = hwMap.dcMotor.get("rightFrontDrive");
        leftRearDrive = hwMap.dcMotor.get("leftRearDrive");
        leftFrontDrive = hwMap.dcMotor.get("leftFrontDrive");

        //set brakes on motor
        rightRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //lift motor set to current configuration
        //Dcmotors: CW by default   cw on left side = forward
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);


        //set pwr to 0
        rightFrontDrive.setPower(0.0);
        leftFrontDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);
        leftRearDrive.setPower(0.0);

    }
}