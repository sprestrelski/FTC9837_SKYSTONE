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
 *  Servo Port 0: blockStealer
 *  Servo Port 5: blockPusher
 ****************************
 */

@TeleOp(name="TeleOp: MecanumTest", group="Linear Opmode")
public class TeleOp_MecanumTest extends LinearOpMode{
    Hardware_MecanumTest pumpkin1 = new Hardware_MecanumTest();
    double clawPosition, rotatePosition, pusherPosition, servoSpeed, rotateSpeed, colorPosition, stealerPosition;
    double MIN_POSITION = 0; double MAX_POSITION = 1;
    double LOWER_CLAW_LIMIT = .2; double UPPER_CLAW_LIMIT = .8;
    double colorCondition;

    @Override
    public void runOpMode() {
        pumpkin1.init(hardwareMap);
        waitForStart();

        clawPosition = .4;
        pusherPosition = MIN_POSITION;

        servoSpeed = .25;
        rotateSpeed = .2;

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
            boolean raiseBar = gamepad1.left_bumper;
            boolean lowerBar = gamepad1.right_bumper;

            if (raiseBar) {
                pumpkin1.FourBarmotor.setPower(.75);
            }
            else if (lowerBar){
                pumpkin1.FourBarmotor.setPower(-.75);
            }
            else{
                pumpkin1.FourBarmotor.setPower(0);
            }

            // if statement removes chance of ArithmeticException (blue = 0), formula adapted from team 5898's color sensor skystone explanation
            //if (pumpkin1.stoneColorS.blue() != 0) colorCondition = pumpkin1.stoneColorS.red() * pumpkin1.stoneColorS.green() / (pumpkin1.stoneColorS.blue() * pumpkin1.stoneColorS.blue());

            //if the color condition is less than two, it's a skystone
            // note: 11/28/2019
            // low lighting conditions means that it detects the orange of the regular stone > the regular skystone,
            // will need to figure out either distance away from blocks/touch sensor/other method to trigger the color
            // sensor regardless of lighting
            colorPosition = (colorCondition < 2 ) ? MAX_POSITION : MIN_POSITION;

            /* OPEN/CLOSE CLAW - dpad down/dpad up */
            // open the claw servo using the DPAD_DOWN button
            if (gamepad1.dpad_down && clawPosition > LOWER_CLAW_LIMIT) clawPosition = clawPosition - servoSpeed;
            // close the claw servo using the DPAD_UP button
            if (gamepad1.dpad_up && clawPosition < UPPER_CLAW_LIMIT) clawPosition = clawPosition + servoSpeed;

            /* ROTATE CLAW - dpad left/dpad right */
            // rotate the claw system servo out using the DPAD_LEFT button if not already at the most open position
            if (gamepad1.dpad_left && rotatePosition < MAX_POSITION) rotatePosition = rotatePosition + rotateSpeed;
            // rotate the claw system servo out  using the DPAD_RIGHT button if not already at the most closed position
            if (gamepad1.dpad_right && rotatePosition > MIN_POSITION) rotatePosition = rotatePosition - rotateSpeed;


            /* BLOCK STEALER - y and x */
            // put UP the block stealer servo using the X button
            if (gamepad1.x && stealerPosition < MAX_POSITION) stealerPosition= stealerPosition + servoSpeed;
            // put DOWN the block stealer servo using the Y button
            if (gamepad1.y && stealerPosition > MIN_POSITION) stealerPosition = stealerPosition - servoSpeed;

            /* BLOCK PUSHER - a/b */
            // rotate the block pusher servo OUT using the A button if not already at the most open position
            if (gamepad1.a && pusherPosition < MAX_POSITION) pusherPosition = 1;
            // rotate the block pusher servo IN using the B button if not already at the most in position
            if (gamepad1.b && pusherPosition > MIN_POSITION) pusherPosition = 0;


            // set the servo values
            pumpkin1.claw.setPosition(Range.clip(clawPosition, MIN_POSITION, MAX_POSITION));
            pumpkin1.rotateClaw.setPosition(Range.clip(rotatePosition, MIN_POSITION, MAX_POSITION));
            pumpkin1.blockPusher.setPosition(Range.clip(pusherPosition, MIN_POSITION, MAX_POSITION));
            pumpkin1.blockStealer.setPosition(Range.clip(stealerPosition, MIN_POSITION,MAX_POSITION));
            pumpkin1.colorTest.setPosition(colorPosition);


            /*
             * TELEMETRY
             * sends info back to driver station using telemetry function
             */

            //telemetry.addData("Distance (cm)", String.format(Locale.US, "%.02f", pumpkin1.distanceCS.getDistance(DistanceUnit.CM)));

            telemetry.addData("CONTROLS", "\nintake: LT   outtake: RT\narmup: RB  armdown: LB\nrotatein: dpad_l  rotateout: dp_r\n\n");
            //servo data
            telemetry.addData("clawPosition", String.format("position=%.2f  actual=%.2f", clawPosition, pumpkin1.claw.getPosition()));
            telemetry.addData("rotatePosition", String.format("position=%.2f  actual=%.2f", rotatePosition, pumpkin1.rotateClaw.getPosition()));
            telemetry.addData("pusherPosition", String.format("position=%.2f  actual=%.2f", pusherPosition, pumpkin1.blockPusher.getPosition()));
            telemetry.addData("stealerPosition", String.format("position=%.2f  actual=%.2f", stealerPosition, pumpkin1.blockStealer.getPosition()));

            //color sensor data
            telemetry.addData("PARK COLOR SENSOR", "");
            telemetry.addData("Alpha", pumpkin1.parkColorS.alpha());
            telemetry.addData("Red  ", pumpkin1.parkColorS.red());
            telemetry.addData("Green", pumpkin1.parkColorS.green());
            telemetry.addData("Blue ", pumpkin1.parkColorS.blue());

            telemetry.addData("STONE COLOR SENSOR", "");
            telemetry.addData("Alpha", pumpkin1.stoneColorS.alpha());
            telemetry.addData("Red  ", pumpkin1.stoneColorS.red());
            telemetry.addData("Green", pumpkin1.stoneColorS.green());
            telemetry.addData("Blue ", pumpkin1.stoneColorS.blue());

            telemetry.update();


        }
    }

}
