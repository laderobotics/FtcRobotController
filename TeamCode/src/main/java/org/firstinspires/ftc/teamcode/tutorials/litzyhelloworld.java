package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class litzyhelloworld extends OpMode {


    @Override
    public void init() {
        telemetry.addData("why do birds fly south in winter","")
    }

    @Override
    public void loop() {
        telemetry.addData("because it's to far to walk!","");
    }
}

