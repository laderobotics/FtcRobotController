package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class VariableExamplejoled extends OpMode {
    @Override
    public void init() {
        int teamNumber = 21797;
        double motorSpeed = 0.75;
        boolean clawClosed = true;
        String name = "Joled Soto";
        int motorAngle = 98;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("motor Speed", motorSpeed);
        telemetry.addData("clawClosed", clawClosed);
        telemetry.addData("Name", name);
        telemetry.addData("Motor angle",motorAngle);
    }

@Override
public void loop()
    /*
    1.Change the String variable "name" to your team name.
    2.Create an int called "motorAngle" and store and angle between 0-180.Display this in your init
    method.
     */
}
