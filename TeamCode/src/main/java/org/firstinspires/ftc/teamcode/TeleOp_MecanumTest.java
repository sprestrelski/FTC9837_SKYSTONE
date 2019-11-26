package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

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
            double leftY = -gamepad1.left_stick_y;


            //driving
            pumpkin1.LFmotor.setPower(leftY + rightX + leftX );
            pumpkin1.RFmotor.setPower(leftY - rightX - leftX);
            pumpkin1.LBmotor.setPower(leftY + rightX - leftX);
            pumpkin1.RBmotor.setPower(leftY - rightX + leftX);


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

            if (pumpkin1.colorS.red() < pumpkin1.colorS.blue()) {
                // we have a blue jewel, do something here
                pumpkin1.colorTest.setPosition(0);
            } else {
                // we have a red jewel, do something here
                pumpkin1.colorTest.setPosition(1);
            }

            if (gamepad1.a) {
                pumpkin1.claw.setPosition(0);
            }
            else if (gamepad1.b){
                pumpkin1.claw.setPosition(1);
            }




            /*
             * TELEMETRY
             * sends info back to driver station using telemetry function
             */

            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", pumpkin1.distanceCS.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", pumpkin1.colorS.alpha());
            telemetry.addData("Red  ", pumpkin1.colorS.red());
            telemetry.addData("Green", pumpkin1.colorS.green());
            telemetry.addData("Blue ", pumpkin1.colorS.blue());

            /*double servoPosition = 0;
            pumpkin1.LClaw.setPosition(servoPosition);
            pumpkin1.RClaw.setPosition(1-servoPosition);
            if(gamepad1.dpad_down) {
                // close claw
                pumpkin1.LClaw.setPosition( pumpkin1.LClaw.getPosition() + .1);
                pumpkin1.RClaw.setPosition( pumpkin1.LClaw.getPosition() + .1);
            } else if (gamepad1.dpad_up) {
                // open claw
                pumpkin1.LClaw.setPosition( pumpkin1.LClaw.getPosition() - .1);
                pumpkin1.RClaw.setPosition( pumpkin1.LClaw.getPosition() - .1);
            }*/


        }
    }

}
