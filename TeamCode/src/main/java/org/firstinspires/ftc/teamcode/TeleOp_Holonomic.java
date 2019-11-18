package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sam on 04/23/2019.

 Adapted from:
 https://github.com/MSMHS-Robotics/FTC4962_Examples/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/ConceptHolonomicDrive.java
 Holonomic concepts from:
 http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
 Robot wheel mapping:
 X FRONT X
 X           X
 X  FL       FR  X
 X
 XXX
 X
 X  BL       BR  X
 X           X
 X       X
 */

@TeleOp(name = "TeleOp: Holonomic_v2", group = "Linear Opmode")
public class TeleOp_Holonomic extends LinearOpMode {
    Hardware_Holonomic robot = new Hardware_Holonomic();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            // left stick controls direction
            // right stick X controls rotation

            float gamepad1LeftY = -gamepad1.left_stick_y;
            float gamepad1LeftX = gamepad1.left_stick_x;
            float gamepad1RightX = gamepad1.right_stick_x;

            // holonomic formulas

            float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

            // clip the right/left values so that the values never exceed +/- 1
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            // write the values to the motors
            robot.rightFrontDrive.setPower(FrontRight);
            robot.leftFrontDrive.setPower(FrontLeft);
            robot.leftRearDrive.setPower(BackLeft);
            robot.rightRearDrive.setPower(BackRight);
        }//end while loop
    }//end runOpMode

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}