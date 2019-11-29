package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Created by Sam on 11/26/19/
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto_Pumpkin: ColorSensorTest", group="Pushbot")
public class Auto_Pumpkin_ColorSensorTest extends LinearOpMode{
    Hardware_MecanumTest autopumpkin = new Hardware_MecanumTest();

    double colorCondition;
    public void runOpMode(){

        autopumpkin.init(hardwareMap);

        waitForStart();

        /*
            Planned Pseudocode
                1. drive forward to stones
                2. move all the way left
                3. move right until see skystone
                4. move servo to grab skystone
                5. move backwards to remove skystone from quarry set up
                6. move right to cross skybridge
         */

        //moveForward(-.75);
        //sleep(3000);
        //stopMovement();
        moveRight(.75);
        sleep(2500);
        stopMovement();

        do {
            if (autopumpkin.stoneColorS.blue() != 0) colorCondition = autopumpkin.stoneColorS.red() * autopumpkin.stoneColorS.green() / (autopumpkin.stoneColorS.blue() * autopumpkin.stoneColorS.blue());
            moveLeft(.25);
        }while (colorCondition < 2);

        do {
            if (autopumpkin.stoneColorS.blue() != 0) colorCondition = autopumpkin.stoneColorS.red() * autopumpkin.stoneColorS.green() / (autopumpkin.stoneColorS.blue() * autopumpkin.stoneColorS.blue());
            moveLeft(.25);
        }while (colorCondition > 2);
        autopumpkin.blockPusher.setPosition(1); 
        stopMovement();
    }

    public void moveRobot(double LF, double LB, double RF, double RB)
    {
        autopumpkin.LFmotor.setPower(LF);
        autopumpkin.LBmotor.setPower(LB);
        autopumpkin.RFmotor.setPower(RF);
        autopumpkin.RBmotor.setPower(RB);
    }
    public void moveForward(double motorPower)
    {
        autopumpkin.LFmotor.setPower(motorPower);
        autopumpkin.LBmotor.setPower(motorPower);
        autopumpkin.RFmotor.setPower(motorPower);
        autopumpkin.RBmotor.setPower(motorPower);
    }

    public void moveLeft(double motorPower)
    {
        autopumpkin.LFmotor.setPower(-motorPower);
        autopumpkin.LBmotor.setPower(motorPower);
        autopumpkin.RFmotor.setPower(motorPower);
        autopumpkin.RBmotor.setPower(-motorPower);
    }

    public void moveRight(double motorPower)
    {
        autopumpkin.LFmotor.setPower(motorPower);
        autopumpkin.LBmotor.setPower(-motorPower);
        autopumpkin.RFmotor.setPower(-motorPower);
        autopumpkin.RBmotor.setPower(motorPower);
    }

    public void stopMovement()
    {
        autopumpkin.LFmotor.setPower(0.0);
        autopumpkin.LBmotor.setPower(0.0);
        autopumpkin.RFmotor.setPower(0.0);
        autopumpkin.RBmotor.setPower(0.0);
    }
}
