package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sam on 9/12/2019.
 */

@TeleOp(name="chad", group="Pushbot")
public class TeleOp_LS2019 extends LinearOpMode{
    Hardware_2MotorChassis_LS2019 chad = new Hardware_2MotorChassis_LS2019();
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
            chad.leftMotor.setPower(leftDrive);
            chad.rightMotor.setPower(rightDrive);

            boolean clawIn = gamepad1.left_bumper;
            boolean clawOut = gamepad1.right_bumper;

            //double clawPosition = Range.clip(gamepad1.right_trigger, 0, 1.0)

            // Use gamepad y and gamepad a buttons to lift and lower the claw
            if (gamepad1.y)
                clawOffset += CLAW_SPEED;
            else if (gamepad1.a)
                clawOffset -= CLAW_SPEED;

            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            //chad.clawMotionServo.setPosition(chad.MID_SERVO + clawOffset);


            //Servos to control claw
            if (clawIn){
                chad.leftServo.setPosition(chad.leftServo.getPosition() >= 0.98 ? 1.0 : chad.leftServo.getPosition() + .02);
                chad.rightServo.setPosition(chad.rightServo.getPosition() >= 0.98 ? 1.0 : chad.rightServo.getPosition() + .02);
            }
            else if (clawOut){
                chad.leftServo.setPosition(chad.leftServo.getPosition() <= 0.25 ? 0.2 : chad.leftServo.getPosition() - .02);
                chad.rightServo.setPosition(chad.rightServo.getPosition() <= 0.25 ? 0.2 : chad.rightServo.getPosition() - .02);
            }
            else {
                chad.leftServo.setPosition(chad.leftServo.getPosition());
                chad.rightServo.setPosition(chad.rightServo.getPosition());
            }


            //chad.clawMotionServo.setPosition(clawPosition);

            /**
             *  Alternative Input Types - doubles/floats
             *  -------------------------------------------
             *  This one can be used just like above.
             *  double leftDrive = gamepad1.left_stick_x;
             *  double rightDrive = gamepad1.right_stick_x;
             *
             *  -------------------------------------------
             *  Only forward movement w/ floats.
             *  double leftDrive = gamepad1.left_trigger;
             *  double rightDrive = gamepad1.right_trigger;
             *  if(leftDrive>0){
             *      chad.leftMotor.setPower(leftDrive);
             *  }
             *  else{
             *      chad.leftMotor.setPower(0.0);
             *  }
             *
             *  if(rightDrive>0){
             *      chad.rightMotor.setPower(rightDrive);
             *  }
             *  else{
             *      chad.rightMotor.setPower(0.0);
             *  }
             *  -------------------------------------------
             *  Only forward movement w/ booleans.
             *  double leftDrive = gamepad1.left_bumper;
             *  double rightDrive = gamepad1.right_bumper;
             *
             *  if (leftDrive){
             *      chad.leftMotor.setPower(1.0);
             *  }
             *  else
             *  {
             *      chad.leftMotor.setPower(0.0);
             *  }
             *
             *  if (rightDrive){
             *      chad.rightMotor.setPower(1.0);
             *  }
             *  else{
             *      chad.rightMotor.setPower(0.0);
             *  }
             *  -------------------------------------------
             *  Four-button forward/backward movement w/ booleans.
             *  double leftForwardDrive = gamepad1.dpad_up;
             *  double rightForwardDrive = gamepad1.y;
             *  double leftBackDrive = gamepad1.dpad_down;
             *  double rightBackDrive = gamepad1.button_a;
             *  double motorPower = 0.8;
             *
             *  if (leftForwardDrive){
             *      chad.leftMotor.setPower(motorPower);
             *  }
             *  else if (leftBackDrive){
             *      chad.leftMotor.setPower(-motorPower);
             *  }
             *  else{
             *      chad.leftMotor.setPower(0.0);
             *  }
             *
             *  if (rightForwardDrive){
             *      chad.rightMotor.setPower(motorPower);
             *  }
             *  else if (rightBackDrive){
             *      chad.rightMotor.setPower(-motorPower);
             *  }
             *  else{
             *      chad.rightMotor.setPower(0.0);
             *  }
             *
             **/


        }
    }
}
