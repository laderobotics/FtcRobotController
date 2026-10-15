package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class litzygamepadpractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //runs 50x* a second
        double speedFoward = -gamepad1.left_stick_y / 2.0;
        double diffXJoysticks = gamepad1.left_stick_x - gamepad1.right_stick_x;
        double sumTriggers = gamepad1.left_trigger + gamepad1.right_trigger;

        telemetry.addData("left x",gamepad1.left_stick_x);
        telemetry.addData("left y",speedFoward);
        telemetry.addData("right x",gamepad1.right_stick_x);
        telemetry.addData("right y",gamepad1.right_stick_y);
        telemetry.addData("diffXJoysticks", diffXJoysticks);

        telemetry.addData("a button", gamepad1.a);
        telemetry.addData("b button",gamepad1.b );

        telemetry.addData("sum Triggers", sumTriggers);
    }
}
