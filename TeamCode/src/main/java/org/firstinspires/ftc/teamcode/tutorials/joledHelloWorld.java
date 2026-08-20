package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class joledHelloWorld extends OpMode {


    @Override
    public void init() {
        telemetry.addData("Why don't eggs tell jokes", "\n")
    }

    @Override
    public void loop() {

    }
}
