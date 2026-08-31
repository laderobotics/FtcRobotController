package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class litzybuttontest extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
      if (gamepad1.a){
          telemetry.addData("hehheheheh",gamepad1.a);
      }
      if (gamepad1.dpad_up){
          telemetry.addData("Hi am a tree.","up");
      }
      if (gamepad1.back){
          telemetry.addData("yayayyayay,gamepad1.back);
      }
      if (gamepad1.b){
          telemetry.addData("The cat in the hat",gamepad2.b);
      }
    }
}
