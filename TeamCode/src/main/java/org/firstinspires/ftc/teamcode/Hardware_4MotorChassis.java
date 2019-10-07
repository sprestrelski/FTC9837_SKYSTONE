package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sam on 9/19/2019.
 */

class Hardware_4MotorChassis {

    //Drive train:
    DcMotor LFmotor;
    DcMotor LBmotor;
    DcMotor RFmotor;
    DcMotor RBmotor;

    public void init(HardwareMap hwMap){

        //set direction of motors facing opposite directions
        LFmotor = hwMap.dcMotor.get("LFdrive");
        RBmotor = hwMap.dcMotor.get("RFdrive");
        LBmotor = hwMap.dcMotor.get("LBdrive");
        RBmotor = hwMap.dcMotor.get("RBdrive");

        //DcMotors: Clockwise by default; clockwise on left side = forward
        LFmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        LBmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        RFmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //set pwr to 0
        LFmotor.setPower(0.0);
        LBmotor.setPower(0.0);
        RFmotor.setPower(0.0);
        RBmotor.setPower(0.0);


    }
}