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

            //rotation - right joystick
            double rightX = gamepad1.right_stick_x;
            //movement - left joystick
            double leftX = gamepad1.left_stick_x;
            double leftY = gamepad1.left_stick_y;
            //wheel/roller intake - right trigger
            double wheelIntake = gamepad1.right_trigger;

            //driving
            pumpkin1.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin1.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin1.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin1.RBmotor.setPower(leftY - rightX + leftX);

            //compliant wheels motors
            pumpkin1.LCompliantmotor.setPower(wheelIntake);
            pumpkin1.RCompliantmotor.setPower(wheelIntake);
        }
    }

}
