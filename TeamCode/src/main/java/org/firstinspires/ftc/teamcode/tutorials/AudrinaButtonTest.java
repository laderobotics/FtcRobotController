package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AudrinaButtonTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            telemetry.addData("hehe", gamepad1.a);
        }
        if (gamepad1.dpad_left) {
            telemetry.addData("im a button", "D left");
        }
        if (gamepad1.b) {
            telemetry.addData("excuse me sir",gamepad1.b);
        }
        if (gamepad1.a) {
            telemetry.addData("HAIII", gamepad1.a);
        }
    }
}



