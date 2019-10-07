package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sam on 9/19/2019.
 */

@TeleOp(name="chad", group="Pushbot")
public class TeleOp_LS20194M extends LinearOpMode{
    Hardware_4MotorChassis_LS2019 chad = new Hardware_4MotorChassis_LS2019();
    double          clawOffset      = 0;                       // Servo mid position
    final double    CLAW_SPEED      = 0.02 ;                   // sets rate to move servo

    @Override
    public void runOpMode(){
        chad.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

            double leftDrive = gamepad1.left_stick_y;   //when looking from top, left motor = port 0
            double rightDrive = gamepad1.right_stick_y; //when looking from top, right motor = port 1

            //driving motors:
            chad.leftFrontMotor.setPower(leftDrive);
            chad.leftBackMotor.setPower(leftDrive);
            chad.rightFrontMotor.setPower(rightDrive);
            chad.rightBackMotor.setPower(rightDrive);

            //boolean clawIn = gamepad1.a;
            //boolean clawOut = gamepad1.y;


            //Servos to control claw
            /*if (clawIn){
                chad.leftServo.setPosition(chad.leftServo.getPosition() >= 0.98 ? 1.0 : chad.leftServo.getPosition() + .02);
                //chad.rightServo.setPosition(chad.rightServo.getPosition() >= 0.98 ? 1.0 : chad.rightServo.getPosition() + .02);
            }
            else if (clawOut){
                //chad.leftServo.setPosition(chad.leftServo.getPosition() <= 0.25 ? 0.2 : chad.leftServo.getPosition() - .02);
                chad.rightServo.setPosition(chad.rightServo.getPosition() <= 0.25 ? 0.2 : chad.rightServo.getPosition() - .02);

            }
            else {
                chad.leftServo.setPosition(chad.leftServo.getPosition());
                chad.rightServo.setPosition(chad.rightServo.getPosition());
            }*/

        }
    }
}
