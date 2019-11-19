package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Sam on 10/16/2019.
 * Edited 10/16: includes motors for the compliant intake
 *
 * LF 0         RF 1
 *      HUB 1
 * LB 2         RB 3
 */

@TeleOp(name="TeleOp: MecanumTest", group="Linear Opmode")
public class TeleOp_MecanumTest extends LinearOpMode{
    Hardware_MecanumTest pumpkin1 = new Hardware_MecanumTest();
    @Override
    public void runOpMode() {
        pumpkin1.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            /*
            //rotation - right joystick
            double rightX = gamepad1.right_stick_x;
            //movement - left joystick
            double leftX = gamepad1.left_stick_x;
            double leftY = gamepad1.left_stick_y;


            //driving
            pumpkin1.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin1.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin1.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin1.RBmotor.setPower(leftY - rightX + leftX);
            */

            //wheel/roller intake - right trigger
            double wheelIntake = gamepad1.right_trigger;
            double wheelOuttake = -gamepad1.left_trigger;
            //compliant wheels motors

            if ( gamepad1.right_trigger > 0 ) {
                pumpkin1.LCompliantmotor.setPower(wheelIntake);
                pumpkin1.RCompliantmotor.setPower(wheelIntake);
            }
            else if ( gamepad1.left_trigger > 0){
                pumpkin1.LCompliantmotor.setPower(wheelOuttake);
                pumpkin1.RCompliantmotor.setPower(wheelOuttake);
            }
            else{
                pumpkin1.LCompliantmotor.setPower(0);
                pumpkin1.RCompliantmotor.setPower(0);
            }


            //four bar - right/left bumper
            boolean raiseBar = gamepad1.right_bumper;
            boolean lowerBar = gamepad1.left_bumper;

            if (raiseBar) {
                pumpkin1.FourBarmotor.setPower(.75);
            }
            else if (lowerBar){
                pumpkin1.FourBarmotor.setPower(-.75);
            }
            else{
                pumpkin1.FourBarmotor.setPower(0);
            }


            //driving
            double left = -gamepad1.left_stick_y;
            double right = -gamepad1.right_stick_y;
            double rightX = gamepad1.right_stick_x;

            // Driving
            if (rightX > 0.5 || rightX < -0.5) {
                double slowRightX = rightX/1.5;
                pumpkin1.LFmotor.setPower(slowRightX);
                pumpkin1.LBmotor.setPower(-slowRightX);
                pumpkin1.RFmotor.setPower(-slowRightX);
                pumpkin1.RBmotor.setPower(slowRightX);
            } else {
                double slowLeft = left/1.5;
                double slowRight = right/1.5;
                pumpkin1.LFmotor.setPower(slowLeft);
                pumpkin1.LBmotor.setPower(slowLeft);
                pumpkin1.RFmotor.setPower(slowRight);
                pumpkin1.RBmotor.setPower(slowRight);
            }
        }
    }

}
