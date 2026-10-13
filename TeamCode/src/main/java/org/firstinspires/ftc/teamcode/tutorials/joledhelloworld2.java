package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Disabled
@Autonomous
public class joledhelloworld2 extends OpMode {

    public void init() {
        telemetry.addData("Hello" , "World"); // My First Comment
    }

    @Override
    public void loop(){
    }

    // Single line Comment
    /*
    1.Hello:World, change the telematry data to display "Hello:Your name"
    2.Run this code in the Autonomous section of your DS
     */
}
