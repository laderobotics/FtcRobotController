package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.tutorials.TestBench;
@Disabled
@TeleOp
public class DistanceSensorPractice extends OpMode {

    TestBench bench = new TestBench();
    double distance = bench.getDistance();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(distance < 10){
            telemetry.addData("Distance","Too close!");
        }
        else{
            telemetry.addData("Distance", distance);
        }
    }
}

/*
1. print "Too close!" if your object is closer than 10cm away.
 */