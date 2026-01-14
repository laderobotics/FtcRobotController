package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class FlywheelTunerTutorial extends OpMode {
    public DcMotorEx flywheelMotor;

    public double highVelocity = 1680;
    public double lowVelocity = 1300;

    double curTargetVelocity = highVelocity;//how the speed changes when you click another button
    double F = 0;// F means Feed forward
    double P = 0;//P means proportional

    double[] stepSizes = {10.0, 1.0, 0.1, 0.01, 0.001};//different levels of sensitivity
    int stepIndex = 1;

    @Override
    public void init() {
    flywheelMotor = hardwareMap.get(DcMotorEx.class,"flywheel_motor");
    flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
    flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    telemetry.addLine("init complete");
    }

    @Override
    public void loop() {
        //get all our gamepad commands
        // get target velocity
        //update telemetry
        if (gamepad1.yWasPressed()) {
            if (curTargetVelocity == highVelocity) {
                curTargetVelocity = lowVelocity;
            }
            else {
                curTargetVelocity = highVelocity;
            }
        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes[stepIndex];
        }
        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes[stepIndex];
        }

        if (gamepad1. dpadUpWasPressed()) {
            P += stepSizes[stepIndex];
        }
        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes[stepIndex];
        }
        //set new PIDF coefeicients
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        //set velocity
        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = -flywheelMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity","%.2f", curVelocity);
        telemetry.addData("Error","%.2f" , error);
        telemetry.addLine("-------------------------");
        telemetry.addData("Tuning P","%.4f (D-Pad U/D)",P);
        telemetry.addData("Tuning F","%.4f (D-Pad L/R)",F);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);



    }
}
