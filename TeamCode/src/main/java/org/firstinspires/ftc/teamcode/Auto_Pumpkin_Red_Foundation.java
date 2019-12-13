package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Created by Sam on 12/12/19.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto_Pumpkin: Grab RED Foundation and PARK", group="Pumpkin: RED")
public class Auto_Pumpkin_Red_Foundation extends LinearOpMode{
    Hardware_MecanumTest autopumpkin = new Hardware_MecanumTest();
    public void runOpMode(){

        autopumpkin.init(hardwareMap);

        waitForStart();

        // set servos to most compact
        autopumpkin.blockPusher.setPosition(1);
        autopumpkin.blockStealer.setPosition(1);

        movement(.75,-.75,-.75,.75); // move RIGHT to align with FOUNDATION
        sleep (1200);

        movement(.75,.75,.75,.75); // move FORWARD to FOUNDATION
        sleep (1400);

        movement(0,0,0,0);
        autopumpkin.blockStealer.setPosition(0); // stops motors, moves servo DOWN
        sleep(500);

        movement(-.75,-.75,-.75,-.75); // move BACKWARDS towards wall
        sleep(1400);

        movement(.75,.75,-.75,-.75); // rotates TOWARD foundation
        sleep(1000);

        movement(0,0,0,0);
        autopumpkin.blockStealer.setPosition(1); // stops motors, moves servo UP
        sleep(500);

        //moves BACKWARD until it sees red
        while (autopumpkin.parkColorS.red() < autopumpkin.parkColorS.blue() || autopumpkin.parkColorS.red() < 100)
        {
            movement(-.5,-.5,-.5,-.5);
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
