package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.TestBench;

@TeleOp
public class DcMotorPractice extends OpMode {

    TestBench bench = new TestBench();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        double motorSpeed = gamepad1.left_stick_y;
        bench.setMotorSpeed(motorSpeed); // control the speed of the motor with left stick y axis

        // turn on the motor if you press the touch sensor
        if(bench.isTouchSensorPressed()){
            bench.setMotorSpeed(0.5); // set motor to half speed
        }
        else{
            bench.setMotorSpeed(0.0); // stops the motor
        }

        // Exercise 1
        if(gamepad1.a){
            bench.setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        else if(gamepad1.b){
            bench.setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        telemetry.addData("motor revs",bench.getMotorRevs()); // print motor revs to driver station

    }
}

/*
1. Add a method on your testBench that allows you to change the brake behavior from your opmode.
On 'a' button pressed, set it to brake. On 'b' button pressed, set it to float.
 */