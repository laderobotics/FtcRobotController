package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LEDTest extends OpMode {

    TestBenchLED bench = new TestBenchLED();


    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a){
            bench.setRedLed(true);
            bench.setGreenLed(false);
        }
        else if (gamepad1.b){
            bench.setGreenLed(true);
            bench.setRedLed(false);
        }
        else if (gamepad1.y) {
            bench.setGreenLed(true);
            bench.setRedLed(true);
        }
    }
}
