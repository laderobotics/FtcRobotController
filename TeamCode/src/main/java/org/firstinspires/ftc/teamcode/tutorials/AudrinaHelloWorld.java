package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AudrinaHelloWorld extends OpMode {


    @Override
    public void init() {
        telemetry.addData("what do you call to guys who love math...","");
    }

    @Override
    public void loop() {
        telemetry.addData("ALGABROS","");
    }
}
