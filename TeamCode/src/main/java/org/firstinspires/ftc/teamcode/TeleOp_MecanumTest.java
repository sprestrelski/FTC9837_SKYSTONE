package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

/**
 * Created by Sam on 10/16/2019.
 * Edited 10/16: includes motors for the compliant intake
 * Edited 11/25-27: servo support, telemetry
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

@TeleOp(name="TeleOp: MecanumTest", group="Linear Opmode")
public class TeleOp_MecanumTest extends LinearOpMode{
    Hardware_MecanumTest pumpkin1 = new Hardware_MecanumTest();
    double clawPosition, rotatePosition, pusherPosition, servoSpeed;
    double MIN_POSITION = 0; double MAX_POSITION = 1;

    @Override
    public void runOpMode() {
        pumpkin1.init(hardwareMap);
        waitForStart();

        clawPosition = .7;
        rotatePosition = MIN_POSITION;
        pusherPosition = MAX_POSITION;

        servoSpeed = .1;

        while (opModeIsActive()) {


            //rotation - right joystick
            double rightX = gamepad1.right_stick_x;
            //movement - left joystick
            double leftX = gamepad1.left_stick_x;
            double leftY = -gamepad1.left_stick_y;


            //driving
            pumpkin1.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin1.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin1.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin1.RBmotor.setPower(leftY - rightX + leftX);


            /* COMPLIANT WHEELS - left trigger/right trigger */
            double wheelOuttake = gamepad1.right_trigger;
            double wheelIntake = -gamepad1.left_trigger;
            //
            if ( gamepad1.left_trigger > 0 ) {
                pumpkin1.LCompliantmotor.setPower(wheelIntake);
                pumpkin1.RCompliantmotor.setPower(wheelIntake);
            }
            else if ( gamepad1.right_trigger > 0){
                pumpkin1.LCompliantmotor.setPower(wheelOuttake);
                pumpkin1.RCompliantmotor.setPower(wheelOuttake);
            }
            else{
                pumpkin1.LCompliantmotor.setPower(0);
                pumpkin1.RCompliantmotor.setPower(0);
            }


            /* FOUR BAR - right/left bumper */
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

            /*if (pumpkin1.colorS.red() < pumpkin1.colorS.blue()) {
                // we have a blue jewel, do something here
                pumpkin1.colorTest.setPosition(0);
            } else {
                // we have a red jewel, do something here
                pumpkin1.colorTest.setPosition(1);
            }*/


            /* OPEN/CLOSE CLAW - a/b */
            // open the claw servo using the A button
            if (gamepad1.a) clawPosition = .7;
            // close the claw servo using the B button
            if (gamepad1.b) clawPosition = 1;

            /* ROTATE CLAW - dpad left/dpad right */
            // rotate the claw system servo out using the DPAD_LEFT button if not already at the most open position
            if (gamepad1.dpad_left && rotatePosition < MAX_POSITION) rotatePosition = rotatePosition + servoSpeed;
            // rotate the claw system servo out  using the DPAD_RIGHT button if not already at the most closed position
            if (gamepad1.dpad_right && rotatePosition > MIN_POSITION) rotatePosition = rotatePosition - servoSpeed;


            /* BLOCK PUSHER - dpad up/dpad down */
            // rotate the block pusher servo OUT using the DPAD_UP button if not already at the most open position
            if (gamepad1.dpad_up && pusherPosition < MAX_POSITION) pusherPosition = pusherPosition + servoSpeed;
            // rotate the block pusher servo IN using the DPAD_DOWN button if not already at the most in position
            if (gamepad1.dpad_down && pusherPosition > MIN_POSITION) pusherPosition = pusherPosition - servoSpeed;


            // set the servo values
            pumpkin1.claw.setPosition(clawPosition);
            pumpkin1.rotateClaw.setPosition(Range.clip(rotatePosition, MIN_POSITION, MAX_POSITION));
            pumpkin1.blockPusher.setPosition(Range.clip(pusherPosition, MIN_POSITION, MAX_POSITION));



            /*
             * TELEMETRY
             * sends info back to driver station using telemetry function
             */

            //telemetry.addData("Distance (cm)", String.format(Locale.US, "%.02f", pumpkin1.distanceCS.getDistance(DistanceUnit.CM)));

            telemetry.addData("CONTROLS", "\nintake: LT   outtake: RT\narmup: RB  armdown: LB\nrotatein: dpL  rotateout: dpR\n\n");
            //servo data
            telemetry.addData("clawPosition", String.format("position=%.2f  actual=%.2f", clawPosition, pumpkin1.claw.getPosition()));
            telemetry.addData("rotatePosition", String.format("position=%.2f  actual=%.2f", rotatePosition, pumpkin1.rotateClaw.getPosition()));
            telemetry.addData("pusherPosition", String.format("position=%.2f  actual=%.2f", pusherPosition, pumpkin1.blockPusher.getPosition()));
            telemetry.addData( "Lolz4lyfe", "hello world");

            //color sensor data
            telemetry.addData("Alpha", pumpkin1.colorS.alpha());
            telemetry.addData("Red  ", pumpkin1.colorS.red());
            telemetry.addData("Green", pumpkin1.colorS.green());
            telemetry.addData("Blue ", pumpkin1.colorS.blue());
            telemetry.update();


        }
    }

}
