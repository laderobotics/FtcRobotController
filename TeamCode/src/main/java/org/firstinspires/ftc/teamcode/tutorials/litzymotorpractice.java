package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp
public class litzymotorpractice extends OpMode {
    litzymotor2 litzymotor2 = new litzymotor2();

    @Override
    public void init() {
        litzymotor2.init(hardwareMap);

    }

    @Override
    public void loop() {
        if (gamepad1.a) {

            litzymotor2.setMotorSpeed(.5);
        }
        else {
            litzymotor2.setMotorSpeed(0.0); // stops the motor

        }
        telemetry.addData("Motor Revs", litzymotor2.getMotorRevs());
    }
}
