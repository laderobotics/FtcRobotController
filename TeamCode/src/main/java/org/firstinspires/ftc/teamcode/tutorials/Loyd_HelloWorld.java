package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Loyd_HelloWorld extends OpMode {


    @Override
    public void init() {
        telemetry.addData("I only wanted to be a math teacher because...","");
    }

    @Override
    public void loop() {



        telemetry.addData("I heard there would be pi!","");
    }
}
