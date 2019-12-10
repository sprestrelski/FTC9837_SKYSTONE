package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Created by Sam on 12/10/19
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto_Pumpkin: Forward//Drive LEFT to Blue", group="Pumpkin: BLUE")
public class Auto_Pumpkin_Forward_DriveToLine extends LinearOpMode{
    Hardware_MecanumTest autopumpkin = new Hardware_MecanumTest();
    public void runOpMode(){

        autopumpkin.init(hardwareMap);

        waitForStart();

        //move FORWARD
        movement(.75,.75,.75,.75);
        sleep (1500);

        //moves LEFT until it sees blue
        while (autopumpkin.parkColorS.red() > autopumpkin.parkColorS.blue() || autopumpkin.parkColorS.blue() < 100)
        {
            movement(-.5,.5,.5,-.5);
        }

        movement(0,0,0,0);
    }

    public void movement(double LF, double LB, double RF, double RB)
    {
        autopumpkin.LFmotor.setPower(LF);
        autopumpkin.LBmotor.setPower(LB);
        autopumpkin.RFmotor.setPower(RF);
        autopumpkin.RBmotor.setPower(RB);
    }
}
