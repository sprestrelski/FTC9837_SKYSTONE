package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

/**
 *  Created by Sam on 9/19/2019.
 ****************************
 *  HORIZONTAL HUB: HUB 2
 *  LC 0         RC 1
 *        HUB 2
 *  FB 2
 *
 *  HUB 2: address 3
 *  Servo Port 0: rotateClaw
 *  Servo Port 4: claw
 ****************************
 *  VERTICAL HUB: HUB 1
 *  LF 0         RF 1
 *        HUB 1
 *  LB 2         RB 3
 *
 *  HUB 1: address 2
 *  Servo Port 5: blockPusher
 ****************************
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

    //Servo LClaw;
    //Servo RClaw;
    Servo claw;
    Servo rotateClaw;
    Servo colorTest;
    Servo blockPusher;
    Servo blockStealer;

    ColorSensor parkColorS;
    ColorSensor stoneColorS;
    DistanceSensor distanceCS;

    public void init(HardwareMap hwMap){

        // assigns names
        LFmotor = hwMap.dcMotor.get("LFmotor");
        LBmotor = hwMap.dcMotor.get("LBmotor");
        RFmotor = hwMap.dcMotor.get("RFmotor");
        RBmotor = hwMap.dcMotor.get("RBmotor");

        LCompliantmotor = hwMap.dcMotor.get("RCmotor");
        RCompliantmotor = hwMap.dcMotor.get("LCmotor");

        FourBarmotor = hwMap.dcMotor.get("4Bmotor");

        claw = hwMap.servo.get("claw");
        rotateClaw = hwMap.servo.get("rotateClaw");
        blockPusher = hwMap.servo.get("blockPusher");
        blockStealer = hwMap.servo.get("blockStealer");
        parkColorS = hwMap.colorSensor.get("parkColorS");
        stoneColorS = hwMap.colorSensor.get("stoneColorS");

        // set brakes on motors
        LFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LCompliantmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RCompliantmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FourBarmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set direction of motors facing opposite directions
        // DcMotors: Clockwise by default; clockwise on left side = forward
        // FRONT = non-compliant wheels
        LFmotor.setDirection(DcMotor.Direction.FORWARD);
        LBmotor.setDirection(DcMotor.Direction.FORWARD);
        RFmotor.setDirection(DcMotor.Direction.REVERSE);
        RBmotor.setDirection(DcMotor.Direction.REVERSE);

        LCompliantmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RCompliantmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FourBarmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        claw.setDirection(Servo.Direction.FORWARD);


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