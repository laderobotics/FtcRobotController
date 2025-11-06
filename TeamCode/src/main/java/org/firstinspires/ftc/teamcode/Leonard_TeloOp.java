//TODO: This is my note.
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Leonard TeleOp", group = "robot")
public class Leonard_TeloOp extends LinearOpMode {

    int myVarible = 2;
    private void launchtest1() {
        DcMotor LaunchMotor = hardwareMap.dcMotor.get("LaunchMotor");
        if (gamepad1.a) {
            LaunchMotor.setPower(1.0)
        }else{
            LaunchMotor.setPower(0.0);
        }
    }

    @Override
    public void runOpMode() {
        //initialization code

        waitForStart();

        while(opModeIsActive()) {
            launchtest1();
        }
    }
}