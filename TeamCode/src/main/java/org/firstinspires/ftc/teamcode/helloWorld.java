package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class helloWorld{
    DcMotor hello;
    DcMotor world;

    public void init(HardwareMap hwMap){
        hello = hwMap.dcMotor.get("rightDrive");
        world = hwMap.dcMotor.get("leftDrive");


    }
}
