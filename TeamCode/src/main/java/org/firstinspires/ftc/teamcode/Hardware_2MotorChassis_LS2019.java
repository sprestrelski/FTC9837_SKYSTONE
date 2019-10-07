package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sam on 9/12/2019.
 */

class Hardware_2MotorChassis_LS2019 {

    //Drive train:
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo leftServo;
    Servo rightServo;
    //Servo clawMotionServo;

    public static final double MID_SERVO       =  0.5 ;

    public void init(HardwareMap hwMap){
        //set direction of motors facing opposite directions
        leftMotor = hwMap.dcMotor.get("leftDrive");
        rightMotor = hwMap.dcMotor.get("rightDrive");
        leftServo = hwMap.servo.get("leftClaw");
        rightServo = hwMap.servo.get("rightClaw");
        //clawMotionServo = hwMap.servo.get("rotateClaw");

        //set servos to original position
        leftServo.setPosition(1);
        rightServo.setPosition(1);
        //clawMotionServo.setPosition(MID_SERVO);

        //DcMotors: Clockwise by default; clockwise on left side = forward
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        //set pwr to 0
        rightMotor.setPower(0.0);
        leftMotor.setPower(0.0);

    }
}