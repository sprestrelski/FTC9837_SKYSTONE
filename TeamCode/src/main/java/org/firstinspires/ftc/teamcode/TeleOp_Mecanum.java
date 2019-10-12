package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Sam on 10/4/2019.
 */

@TeleOp(name="TeleOp: Mecanum", group="Linear Opmode")
public class TeleOp_Mecanum extends LinearOpMode{
    Hardware_4MotorChassis robot = new Hardware_4MotorChassis();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            //rotation - right joystick
            double rightX = gamepad1.left_stick_x;
            //movement - left joystick
            double leftX = gamepad1.right_stick_x;
            double leftY = gamepad1.right_stick_y;
            //wheel/roller intake - right trigger
            double wheelIntake = gamepad1.right_trigger;

            //driving
            robot.LFmotor.setPower(leftY + rightX + leftX );
            robot.RFmotor.setPower(leftY - rightX - leftX);
            robot.LBmotor.setPower(leftY + rightX - leftX);
            robot.RBmotor.setPower(leftY - rightX + leftX);

            //compliant wheels motors
            //robot.LCompliantmotor.setPower(wheelIntake);
            //robot.RCompliantmotor.setPower(wheelIntake);
        }
    }

}
