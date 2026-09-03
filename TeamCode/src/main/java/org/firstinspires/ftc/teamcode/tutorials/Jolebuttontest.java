package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class Jolebuttontest extends OpMode {


    @Override
    public void init() {

    }

    @Override
    public void loop() {
            if(gamepad1.a) {
                telemetry.addData("the power friendship",gamepad1.a);
            }
            if (gamepad1.b){
                telemetry.addData("lorax",gamepad1.b);
            }
            if (gamepad1.back) {
                telemetry.addData("hunger games",gamepad1.back);
            }
            if (gamepad1.dpad_down) {
                telemetry.addData("let it go",gamepad1.dpad_down);
            }
    }
}
