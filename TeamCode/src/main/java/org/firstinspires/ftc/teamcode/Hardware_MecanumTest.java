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
    //Servo colorTest;
    Servo blockPusher;

    ColorSensor colorS;
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

        //colorTest = hwMap.servo.get("colorTest");
        colorS = hwMap.colorSensor.get("colorS");

        // set brakes on motors
        LFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set direction of motors facing opposite directions
        // DcMotors: Clockwise by default; clockwise on left side = forward
        LFmotor.setDirection(DcMotor.Direction.REVERSE);
        LBmotor.setDirection(DcMotor.Direction.REVERSE);
        RFmotor.setDirection(DcMotor.Direction.FORWARD);
        RBmotor.setDirection(DcMotor.Direction.FORWARD);

        LCompliantmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RCompliantmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FourBarmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //LClaw.setDirection(Servo.Direction.FORWARD);
        //RClaw.setDirection(Servo.Direction.REVERSE);
        claw.setDirection(Servo.Direction.FORWARD);


        //set pwr to 0
        LFmotor.setPower(0.0);
        LBmotor.setPower(0.0);
        RFmotor.setPower(0.0);
        RBmotor.setPower(0.0);

        LCompliantmotor.setPower(0.0);
        RCompliantmotor.setPower(0.0);
        FourBarmotor.setPower(0);

        //rotateClaw.setPosition(1);
        //blockPusher.setPosition(0);
        //colorTest.setPosition(0);

    }
}