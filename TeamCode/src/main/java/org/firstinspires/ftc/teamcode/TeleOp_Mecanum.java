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
    Hardware_4MotorChassis pumpkin = new Hardware_4MotorChassis();
    @Override
    public void runOpMode() {
        pumpkin.init(hardwareMap);
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
            pumpkin.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin.RBmotor.setPower(leftY - rightX + leftX);

            //compliant wheels motors
            //pumpkin.LCompliantmotor.setPower(wheelIntake);
            //pumpkin.RCompliantmotor.setPower(wheelIntake);

            //pumpkin.4barmotor.setPower(0);
        }
    }

}
