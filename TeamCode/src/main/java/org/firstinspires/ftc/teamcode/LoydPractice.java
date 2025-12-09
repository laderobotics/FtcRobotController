package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class LoydPractice extends OpMode {

    @Override
    public void init() {

    }


    @Override
    public void loop() {
        /* Gamepad Practice video
        //Runs ~50x per second
        double speedForward = -gamepad1.left_stick_y / 2.0;
        double xDifference = gamepad1.left_stick_x-gamepad1.right_stick_x;
        double triggerSum = gamepad1.left_trigger + gamepad1.right_trigger;

        telemetry.addData("left x",gamepad1.left_stick_x);
        telemetry.addData("left y",gamepad1.left_stick_y);
        telemetry.addData("right x",gamepad1.right_stick_x);
        telemetry.addData("right y",gamepad1.right_stick_y);
        telemetry.addData("a button",gamepad1.a);
        telemetry.addData("b button",gamepad1.b);
        telemetry.addData("speed forward",speedForward);
        telemetry.addData("x difference",xDifference);
        telemetry.addData("trigger sum",triggerSum);
        */

        /* IfPractice Video
        boolean aButton = gamepad1.a; // press TRUE, depress FALSE
        //true or false
        if(aButton){
            telemetry.addData("A Button", "Pressed!");
        }
        else {
            telemetry.addData("A Button", "NOT Pressed");
        }
        telemetry.addData("A Button State",aButton);

        double leftStickY = gamepad1.left_stick_y;
        if(leftStickY < 0.0){
            telemetry.addData("Left stick","negative!");
        }
        else if (leftStickY>0.5){
            telemetry.addData("Left stick","more than 50%");
        }
        else if(leftStickY > 0.0){
            telemetry.addData("Left stick", "positive!");
        }
        else {
            telemetry.addData("Left stick","zero!");
        }
        //Dead zone example
        if(leftStickY<0.1 && leftStickY>-0.1){
            telemetry.addData("Left stick","dead zone");
        }
        telemetry.addData("Left stick value",leftStickY);

        //1. Turbo button
        double motorSpeed = gamepad1.left_stick_y;
        boolean turboButton = gamepad1.a;
        if(!turboButton){
            motorSpeed *= 0.5;
        }

        // AND - &&  Ex: if(leftY < 0.5 && leftY > 0){}
        // OR - ||
        // NOT - ! (in front of a boolean/conditional statement)
        */


    }
}
