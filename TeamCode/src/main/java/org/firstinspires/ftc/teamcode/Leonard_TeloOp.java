//TODO: This is my note.
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp(name = "Leonard TeleOp", group = "robot")
public class Leonard_TeloOp extends LinearOpMode {

    int myVarible = 2;
    private CRServo turningtableservo;
    private void launchtest1() {
        DcMotor LaunchMotor = hardwareMap.dcMotor.get("LaunchMotor");
        if (gamepad1.a) {
            LaunchMotor.setPower(0.5);
        } else if (gamepad1.b) {
            LaunchMotor.setPower(0.4);
        } else if (gamepad1.y) {
            LaunchMotor.setPower(0.3);
        } else if (gamepad1.x) {
            LaunchMotor.setPower(0.2);
        } else{
            LaunchMotor.setPower(0.0);
        }
    }
    private void turnTable() {
        turningtableservo = hardwareMap.get(CRServo.class, "turningtableservo");
        if (gamepad1.left_bumper) { // Example: Press 'A' button to turn clockwise
            turningtableservo.setPower(-1.0); // Adjust speed as needed
        } else if (gamepad1.right_bumper) { // =Example: Press 'B' button to turn counter-clockwise
            turningtableservo.setPower(1.0); // Adjust speed as needed
        } else {
            turningtableservo.setPower(0.0); // Stop when no button is pressed
        }
    }








    @Override
    public void runOpMode() {
        //initialization code

        waitForStart();

        while(opModeIsActive()) {
            launchtest1();
            turnTable();
        }
    }
}































































































