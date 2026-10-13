package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class litzyhelloworld2 extends OpMode {
    @Override
    public void init() {
        telemetry.addData("hello","World");// My first comment
    }

    @Override
    public void loop() {
    }
    // single line comment
    /*
    1.Heloo:world, change the telemetry data to display "Helo:Your" name
     */
}
