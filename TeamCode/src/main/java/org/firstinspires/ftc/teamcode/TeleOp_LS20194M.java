package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sam on 9/19/2019.
 */

@TeleOp(name="LS2019", group="Pushbot")
public class TeleOp_LS20194M extends LinearOpMode{
    Hardware_4MotorChassis_LS2019 LS2019 = new Hardware_4MotorChassis_LS2019();
    double          clawOffset      = 0;                       // Servo mid position
    final double    CLAW_SPEED      = 0.02 ;                   // sets rate to move servo

    @Override
    public void runOpMode(){
        LS2019.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

            double leftDrive = gamepad1.left_stick_y;   //when looking from top, left motor = port 0
            double rightDrive = gamepad1.right_stick_y; //when looking from top, right motor = port 1

            //driving motors:
            LS2019.leftFrontMotor.setPower(leftDrive);
            LS2019.leftBackMotor.setPower(leftDrive);
            LS2019.rightFrontMotor.setPower(rightDrive);
            LS2019.rightBackMotor.setPower(rightDrive);

            //boolean clawIn = gamepad1.a;
            //boolean clawOut = gamepad1.y;


            //Servos to control claw
            /*if (clawIn){
                LS2019.leftServo.setPosition(LS2019.leftServo.getPosition() >= 0.98 ? 1.0 : LS2019.leftServo.getPosition() + .02);
                //LS2019.rightServo.setPosition(LS2019.rightServo.getPosition() >= 0.98 ? 1.0 : LS2019.rightServo.getPosition() + .02);
            }
            else if (clawOut){
                //LS2019.leftServo.setPosition(LS2019.leftServo.getPosition() <= 0.25 ? 0.2 : LS2019.leftServo.getPosition() - .02);
                LS2019.rightServo.setPosition(LS2019.rightServo.getPosition() <= 0.25 ? 0.2 : LS2019.rightServo.getPosition() - .02);

            }
            else {
                LS2019.leftServo.setPosition(LS2019.leftServo.getPosition());
                LS2019.rightServo.setPosition(LS2019.rightServo.getPosition());
            }*/

        }
    }
}
