package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Lift;

@TeleOp
public class LeonardsLiftPracticeTest extends OpMode {

    Lift lift=new Lift();
    @Override
    public void init() {
        lift.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up){
            if(!lift.getLiftSensorTop()){
                lift.setLiftMotorPower(1.0);
            }
            else {
                lift.setLiftMotorPower(0.0);
            }
        }
        else {
            if (!lift.getLiftSensorBottom()){
                lift.setLiftMotorPower(-1.0);
            }
            else {
                lift.setLiftMotorPower(0.0);
            }
        }
        telemetry.addData("Motor Power",lift.getLiftMotorPower());
        telemetry.addData("Top Sensor",lift.getLiftSensorTop());
        telemetry.addData("Bottom Sensor",lift.getLiftSensorBottom());
    }
}
