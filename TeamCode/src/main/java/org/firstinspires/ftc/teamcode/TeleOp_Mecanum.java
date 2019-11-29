package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import java.util.Locale;

/**
 * Created by Sam on 10/4/2019.
 * Final TeleOp for the mecanum chassis Skystone robot.
 ****************************
 *  HORIZONTAL HUB: HUB 2
 *  LC 0         RC 1
 *        HUB 2
 *  FB 2
 *
 *  HUB 2: address 3
 *  Servo Port 0: rotateClaw
 *  Servo Port 4: claw
 ****************************
 *  VERTICAL HUB: HUB 1
 *  LF 0         RF 1
 *        HUB 1
 *  LB 2         RB 3
 *
 *  HUB 1: address 2
 *  Servo Port 5: blockPusher
 ****************************
 */

@TeleOp(name="TeleOp: Mecanum", group="Linear Opmode")
public class TeleOp_Mecanum extends LinearOpMode{
    Hardware_Pumpkin pumpkin = new Hardware_Pumpkin();
    double clawPosition, rotatePosition, pusherPosition, servoSpeed;
    double MIN_POSITION = 0; double MAX_POSITION = 1;

    @Override
    public void runOpMode() {
        pumpkin.init(hardwareMap);
        waitForStart();

        clawPosition = .4;
        rotatePosition = MIN_POSITION;
        pusherPosition = MIN_POSITION;

        servoSpeed = .1;

        while (opModeIsActive()) {


            //rotation - right joystick
            double rightX = gamepad1.right_stick_x;
            //movement - left joystick
            double leftX = gamepad1.left_stick_x;
            double leftY = -gamepad1.left_stick_y;


            //driving
            pumpkin.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin.RBmotor.setPower(leftY - rightX + leftX);


            /* COMPLIANT WHEELS - left trigger/right trigger */
            double wheelOuttake = gamepad1.right_trigger;
            double wheelIntake = -gamepad1.left_trigger;
            //
            if ( gamepad1.left_trigger > 0 ) {
                pumpkin.LCompliantmotor.setPower(wheelIntake);
                pumpkin.RCompliantmotor.setPower(wheelIntake);
            }
            else if ( gamepad1.right_trigger > 0){
                pumpkin.LCompliantmotor.setPower(wheelOuttake);
                pumpkin.RCompliantmotor.setPower(wheelOuttake);
            }
            else{
                pumpkin.LCompliantmotor.setPower(0);
                pumpkin.RCompliantmotor.setPower(0);
            }


            /* FOUR BAR - right/left bumper */
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

            /* OPEN/CLOSE CLAW - dpad_up */
            // open the claw servo using the DPAD_UP button
            if (gamepad1.dpad_up && clawPosition > MIN_POSITION) clawPosition = clawPosition - .05;
            // close the claw servo using the DPAD_DOWN button
            if (gamepad1.dpad_down && clawPosition < MAX_POSITION) clawPosition = clawPosition + .05;

            /* ROTATE CLAW - dpad left/dpad right */
            // rotate the claw system servo out using the DPAD_LEFT button if not already at the most open position
            if (gamepad1.dpad_left && rotatePosition < MAX_POSITION) rotatePosition = rotatePosition + servoSpeed;
            // rotate the claw system servo out  using the DPAD_RIGHT button if not already at the most closed position
            if (gamepad1.dpad_right && rotatePosition > MIN_POSITION) rotatePosition = rotatePosition - servoSpeed;


            /* BLOCK PUSHER - a/b */
            // rotate the block pusher servo OUT using the A button if not already at the most open position
            if (gamepad1.a && pusherPosition < MAX_POSITION) pusherPosition = pusherPosition + servoSpeed;
            // rotate the block pusher servo IN using the B button if not already at the most in position
            if (gamepad1.b && pusherPosition > MIN_POSITION) pusherPosition = pusherPosition - servoSpeed;


            // set the servo values
            pumpkin.claw.setPosition(Range.clip(clawPosition, MIN_POSITION, MAX_POSITION));
            pumpkin.rotateClaw.setPosition(Range.clip(rotatePosition, MIN_POSITION, MAX_POSITION));
            pumpkin.blockPusher.setPosition(Range.clip(pusherPosition, MIN_POSITION, MAX_POSITION));


            /*
             * TELEMETRY
             * sends info back to driver station using telemetry function
             */

            telemetry.addData("CONTROLS",  "\nintake: LT        outtake: RT" +
                                                        "\narmup: RB         armdown: LB" +
                                                        "\nrotatein: dpad_l  rotateout: dp_r\n\n");
            //servo data
            telemetry.addData("clawPosition", String.format("position=%.2f  actual=%.2f", clawPosition, pumpkin.claw.getPosition()));
            telemetry.addData("rotatePosition", String.format("position=%.2f  actual=%.2f", rotatePosition, pumpkin.rotateClaw.getPosition()));
            telemetry.addData("pusherPosition", String.format("position=%.2f  actual=%.2f", pusherPosition, pumpkin.blockPusher.getPosition()));

            //color sensor data
            telemetry.addData("Alpha", pumpkin.parkColorS.alpha());
            telemetry.addData("Red  ", pumpkin.parkColorS.red());
            telemetry.addData("Green", pumpkin.parkColorS.green());
            telemetry.addData("Blue ", pumpkin.parkColorS.blue());
            telemetry.update();


        }
    }

}
