package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Created by Sam on 11/26/19/
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto_Pumpkin: Drive Right to Blue", group="Pushbot")
public class Auto_Pumpkin_DriveToLine extends LinearOpMode{
    Hardware_MecanumTest autopumpkin = new Hardware_MecanumTest();
    public void runOpMode(){

        autopumpkin.init(hardwareMap);

        waitForStart();

        //moves left until it sees blue
        while (autopumpkin.parkColorS.red() > autopumpkin.parkColorS.blue())
        {
            autopumpkin.LFmotor.setPower(-1);
            autopumpkin.LBmotor.setPower(1);
            autopumpkin.RFmotor.setPower(1);
            autopumpkin.RBmotor.setPower(-1);
        }

        autopumpkin.LFmotor.setPower(0.0);
        autopumpkin.LBmotor.setPower(0.0);
        autopumpkin.RFmotor.setPower(0.0);
        autopumpkin.RBmotor.setPower(0.0);

        /*
        tank.liftUpMotor.setPower(1.0);
        sleep( 5300);//lower: 4 sec; 4.75, 4.82,
        tank.liftUpMotor.setPower(0.0);
        sleep(500);
        //raise: 8.5
        tank.leftDrive.setPower(1.0);
        tank.rightDrive.setPower(1.0);
        sleep(1800);

        tank.leftDrive.setPower(1.0);
        tank.rightDrive.setPower(-1.0);
        sleep(1000);

        tank.leftDrive.setPower(1.0);
        tank.rightDrive.setPower(1.0);
        sleep(5000);

        tank.leftDrive.setPower(0.0);
        tank.rightDrive.setPower(0.0);
        */

    }
}
