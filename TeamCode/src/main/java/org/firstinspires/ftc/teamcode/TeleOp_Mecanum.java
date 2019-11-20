package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Sam on 10/4/2019.
 *
 * LF 0         RF 1
 *      HUB 1
 * LB 2         RB 3
 *
 * LC 0         RC 1
 *      HUB 2
 *
 */

@TeleOp(name="TeleOp: Mecanum", group="Linear Opmode")
public class TeleOp_Mecanum extends LinearOpMode{
    Hardware_Pumpkin pumpkin = new Hardware_Pumpkin();
    @Override
    public void runOpMode() {
        pumpkin.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            //**MOVEMENT**//

            //rotation - right joystick
            double rightX = gamepad1.right_stick_x;
            //movement - left joystick
            double leftX = gamepad1.left_stick_x;
            double leftY = gamepad1.left_stick_y;

            //driving
            pumpkin.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin.RBmotor.setPower(leftY - rightX + leftX);


            //**COMPLIANT WHEEL INTAKE**//

            //wheel/roller intake - right trigger
            double wheelIntake = gamepad1.right_trigger;
            double wheelOuttake = -gamepad1.left_trigger;

            //compliant wheels motors
            if ( gamepad1.right_trigger > 0 ) {
                pumpkin.LCompliantmotor.setPower(wheelIntake);
                pumpkin.RCompliantmotor.setPower(wheelIntake);
            }
            else if ( gamepad1.left_trigger > 0){
                pumpkin.LCompliantmotor.setPower(wheelOuttake);
                pumpkin.RCompliantmotor.setPower(wheelOuttake);
            }
            else{
                pumpkin.LCompliantmotor.setPower(0);
                pumpkin.RCompliantmotor.setPower(0);
            }


            //**FOUR BAR LIFT**//

            //four bar - right/left bumper
            boolean raiseBar = gamepad1.right_bumper;
            boolean lowerBar = gamepad1.left_bumper;

            if (raiseBar) {
                pumpkin.FourBarmotor.setPower(.75);
            }
            else if (lowerBar){
                pumpkin.FourBarmotor.setPower(-.75);
            }
            else{
                pumpkin.FourBarmotor.setPower(0);
            }
        }
    }

}
