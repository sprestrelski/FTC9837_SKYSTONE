package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sam on 9/19/2019.
 */

class Hardware_4MotorChassis_LS2019 {

    //Drive train:
    DcMotor leftFrontMotor;
    DcMotor leftBackMotor;
    DcMotor rightFrontMotor;
    DcMotor rightBackMotor;
    Servo leftServo;
    Servo rightServo;

    public void init(HardwareMap hwMap){
        //set direction of motors facing opposite directions
        leftFrontMotor = hwMap.dcMotor.get("leftFrontDrive");
        rightFrontMotor = hwMap.dcMotor.get("rightFrontDrive");
        leftBackMotor = hwMap.dcMotor.get("leftBackDrive");
        rightBackMotor = hwMap.dcMotor.get("rightBackDrive");

        //leftServo = hwMap.servo.get("leftClaw");
        //rightServo = hwMap.servo.get("rightClaw");

        //set servos to original position
        //leftServo.setPosition(.5);
        //rightServo.setPosition(.5);

        //DcMotors: Clockwise by default; clockwise on left side = forward
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        //set pwr to 0
        leftFrontMotor.setPower(0.0);
        leftBackMotor.setPower(0.0);
        rightFrontMotor.setPower(0.0);
        rightBackMotor.setPower(0.0);


    }
}