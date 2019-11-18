package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 *  Created by Sam on 9/19/2019.
 *  LC 0         RC 1
 *        HUB 2
 *  FB 2
 *
 *  HUB 2: address 3
 *
 *  LF 0         RF 1
 *        HUB 1
 *  LB 2         RB 3
 *
 *  HUB 1: address 2
 */

class Hardware_MecanumTest {

    //Drive train:
    DcMotor LFmotor;
    DcMotor LBmotor;
    DcMotor RFmotor;
    DcMotor RBmotor;

    DcMotor LCompliantmotor;
    DcMotor RCompliantmotor;

    DcMotor FourBarmotor;

    public void init(HardwareMap hwMap){

        // assigns names
        LFmotor = hwMap.dcMotor.get("LFmotor");
        LBmotor = hwMap.dcMotor.get("LBmotor");
        RFmotor = hwMap.dcMotor.get("RFmotor");
        RBmotor = hwMap.dcMotor.get("RBmotor");

        LCompliantmotor = hwMap.dcMotor.get("RCmotor");
        RCompliantmotor = hwMap.dcMotor.get("LCmotor");

        FourBarmotor = hwMap.dcMotor.get("4Bmotor");


        // set brakes on motors
        LFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set direction of motors facing opposite directions
        // DcMotors: Clockwise by default; clockwise on left side = forward
        LFmotor.setDirection(DcMotor.Direction.FORWARD);
        LBmotor.setDirection(DcMotor.Direction.FORWARD);
        RFmotor.setDirection(DcMotor.Direction.REVERSE);
        RBmotor.setDirection(DcMotor.Direction.REVERSE);

        LCompliantmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        RCompliantmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //set pwr to 0
        LFmotor.setPower(0.0);
        LBmotor.setPower(0.0);
        RFmotor.setPower(0.0);
        RBmotor.setPower(0.0);

        LCompliantmotor.setPower(0.0);
        RCompliantmotor.setPower(0.0);
        FourBarmotor.setPower(0);

    }
}