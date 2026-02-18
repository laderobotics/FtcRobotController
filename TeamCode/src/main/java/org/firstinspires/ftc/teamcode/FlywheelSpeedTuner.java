package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
@Disabled
@TeleOp
public class FlywheelSpeedTuner extends OpMode {
    public DcMotorEx flywheelMotor;
    Launcher launcher = new Launcher();

    public double velocity = 890;

    double[] stepSizes = {1000.0, 100.0, 10.0};//different levels of sensitivity
    int stepIndex = 1;

    @Override
    public void init() {
    flywheelMotor = hardwareMap.get(DcMotorEx.class,"flywheel_motor");
    flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    flywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0,0,0,13.5);
    flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    launcher.init(hardwareMap);
    launcher.setLauncherServoPosition(0.33);
    telemetry.addLine("init complete");
    }

    @Override
    public void loop() {
        //get all our gamepad commands
        // get target velocity
        //update telemetry
        if (gamepad1.dpad_up) {
            launcher.setLauncherServoPosition(0.0);
        }
        else{
            launcher.setLauncherServoPosition(0.33);
        }

        if (gamepad1.dpadRightWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.yWasPressed()) {
            velocity += stepSizes[stepIndex];
        }
        if (gamepad1.aWasPressed()) {
            velocity -= stepSizes[stepIndex];
        }

        //set velocity
        flywheelMotor.setVelocity(velocity);

        double curVelocity = flywheelMotor.getVelocity();
        double error = velocity - curVelocity;

        telemetry.addData("target Velocity", velocity);
        telemetry.addData("Current Velocity","%.2f", curVelocity);
        telemetry.addData("Error","%.2f" , error);
        telemetry.addLine("-------------------------");
        telemetry.addData("Step Size", "%.4f (dPad Right)", stepSizes[stepIndex]);



    }
}
