package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class litzymotor extends OpMode {


    @Override
    public void init() {

    }

    @Override
    public void loop() {

        double vertical = -1*gamepad1.left_stick_y;
        double horizontal = gamepad1.left_stick_x;
        double rotation = gamepad1.right_stick_x;

    }
}
