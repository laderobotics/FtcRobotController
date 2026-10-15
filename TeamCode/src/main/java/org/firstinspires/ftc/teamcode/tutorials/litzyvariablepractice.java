package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class litzyvariablepractice extends OpMode {
    @Override
    public void init() {
        int teamNumber = 21797;
        double motorSpeed =.75;
        boolean clawClose = true;
        String name= "litzy";
        int motorAngle = 90;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData( "Motor Speed", motorSpeed);
        telemetry.addData("Claw Close",clawClose);
        telemetry.addData("Name",name);
        telemetry.addData("motor angle", motorAngle);
    }

    @Override
    public void loop() {

    }
}

