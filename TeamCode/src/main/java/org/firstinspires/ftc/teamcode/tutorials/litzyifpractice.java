package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class litzyifpractice extends OpMode {

    @Override
    public void init() {

    }


    @Override
    public void loop() {
        boolean aButton = gamepad1.a;// press TRUE, depress FALSE

        if (aButton){
            telemetry.addData("A Button", "Pressed");
        }
        else {
            telemetry.addData("A Button","NOT Pressed");
        }
        telemetry.addData("A Button State",aButton);
    }
}
