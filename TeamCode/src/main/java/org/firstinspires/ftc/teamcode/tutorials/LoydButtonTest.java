package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LoydButtonTest extends OpMode {


    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            telemetry.addData("That tickles!",gamepad1.a);
        }
        if(gamepad1.dpad_left){
            telemetry.addData("Wow, you found the button!","D left");
        }
    }
}
