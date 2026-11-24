package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class LaunchSpeedTuner extends OpMode {
    public DcMotorEx flywheelMotor;


    public double targetVelocity = 1500;
    //public double lowVelocity = 900;
    double curTargetVelocity = targetVelocity;

    double F = 16;
    double P = 210;

    double[] stepSizes={1000,100,10};

    int stepIndex = 1;


    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "motor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

    }

    @Override
    public void loop() {
        // get all our gamepad commands
        // set target velocity
        // update telemetry


        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadUpWasPressed()) {
            curTargetVelocity += stepSizes[stepIndex];
        }
        if (gamepad1.dpadDownWasPressed()) {
            curTargetVelocity -= stepSizes[stepIndex];
        }

        // set new PIDF coefficients
        //PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        //flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

        // set velocity
        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = flywheelMotor.getVelocity();
        double error = curVelocity - curVelocity;

        telemetry.addData("target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity","%.2f", curVelocity);
        telemetry.addData("Error","%.2f" , error);
        telemetry.addLine("-------------------------");
        //telemetry.addData("Tuning P","%.4f (D-Pad U/D)",P);
        //telemetry.addData("Tuning F","%.4f (D-Pad L/R)",F);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);


    }
}
