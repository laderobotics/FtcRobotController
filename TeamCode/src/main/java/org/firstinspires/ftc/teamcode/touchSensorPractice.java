package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBench;

@Disabled
@TeleOp
public class touchSensorPractice extends OpMode {
    TestBench bench = new TestBench();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(bench.isTouchSensorPressed()){
            telemetry.addData("touch sensor state","pressed");
        }
        else{
            telemetry.addData("touch sensor state","not pressed");
        }

    }
}

/*
   1. create a new getter method in testBench class called "isTouchSensorReleased"
   should return true if the touch sensor is NOT being pressed.
   2. in your telemetry opMode, have telemetry state "pressed" or "not pressed"
   instead of true and false.
 */
