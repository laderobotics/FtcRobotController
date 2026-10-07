package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp
public class hackcitypractise extends OpMode {
    hackcitybench hackcitybench = new hackcitybench();

    @Override
    public void init() {
        hackcitybench.init(hardwareMap);

    }

    @Override
    public void loop() {
    if (gamepad1.a) {
        hackcitybench.setMotorSpeed(gamepad1.right_stick_y);
        hackcitybench.setMotorSpeed(0.5); // stops the motor
    }
    else {
        hackcitybench.setMotorSpeed(0.0); // stops the motor

    }
    telemetry.addData("Motor Revs", hackcitybench.getMotorRevs());
    }

}
