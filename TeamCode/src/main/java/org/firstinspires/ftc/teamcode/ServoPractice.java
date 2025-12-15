package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.tutorials.TestBench;
@Disabled
@TeleOp
public class ServoPractice extends OpMode {

    TestBench bench = new TestBench();
    double posTrigger,crTrigger;

    @Override
    public void init() {
        bench.init(hardwareMap);
        posTrigger = 0.0;
        crTrigger = 0.0;
    }

    @Override
    public void loop() {
        posTrigger = gamepad1.left_trigger;
        crTrigger = gamepad1.right_trigger;

        if(gamepad1.a){
            bench.setServoPos(0.0);
        }
        else{
            bench.setServoPos(1.0);
        }
        if(gamepad1.b){
            bench.setServoCRPower(1.0);
        }
        else{
            bench.setServoCRPower(0.0);
        }

        bench.setServoPos(posTrigger);
        bench.setServoCRPower(crTrigger);

    }
}

/*
1. Set your CR to reverse its direction. (done in TestBench)
2. Set your opmode so that when you pull your left gamepad trigger, it sets the position of the pos servo
and when you pull the right gamepad trigger, 0 is off and 1 is fully on.
 */
