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
            double rotationControl = gamepad1.left_stick_x;
            //movement - left joystick
            double leftX = gamepad1.right_stick_x;
            double leftY = gamepad1.right_stick_y;
            //wheel/roller intake - right trigger


            //driving - movement
            /*if ( (leftX > 0.5 && leftY > 0.5)  || (leftX < -0.5 && leftY <-0.5 ) ) {
                //move diagonally y=x
                robot.LFmotor.setPower(leftX);
                robot.LBmotor.setPower(-leftX);
                robot.RFmotor.setPower(-leftX);
                robot.RBmotor.setPower(leftX);
            } else */

            if (leftX > 0.5) {
                //move right
                robot.LFmotor.setPower(leftX);
                robot.LBmotor.setPower(-leftX);
                robot.RFmotor.setPower(-leftX);
                robot.RBmotor.setPower(leftX);
            } else if (leftX < -0.5) {
                //move left
                robot.LFmotor.setPower(-leftX);
                robot.LBmotor.setPower(leftX);
                robot.RFmotor.setPower(leftX);
                robot.RBmotor.setPower(-leftX);
            } else if (leftY > 0.5 || leftY < -0.5){
                //move front and back only
                robot.LFmotor.setPower(leftX);
                robot.LBmotor.setPower(leftX);
                robot.RFmotor.setPower(leftX);
                robot.RBmotor.setPower(leftX);
            }


            /*
            double left = -gamepad1.left_stick_y;
            double right = -gamepad1.right_stick_y;
            double leftX = gamepad1.right_stick_x;
            */

            /* Driving
            if (leftX > 0.5 || leftX < -0.5) {
                double slowleftX = leftX/1.5;
                robot.leftFrontMotor.setPower(slowleftX);
                robot.leftBackMotor.setPower(-slowleftX);
                robot.rightFrontMotor.setPower(-slowleftX);
                robot.rightBackMotor.setPower(slowleftX);
            } else {
                double slowLeft = left/1.5;
                double slowRight = right/1.5;
                robot.leftFrontMotor.setPower(slowLeft);
                robot.leftBackMotor.setPower(slowLeft);
                robot.rightFrontMotor.setPower(slowRight);
                robot.rightBackMotor.setPower(slowRight);
            }*/

        }
    }

}
