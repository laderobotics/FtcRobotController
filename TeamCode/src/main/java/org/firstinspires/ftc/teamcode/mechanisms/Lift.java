package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    private DcMotor liftMotor;
    private RevTouchSensor liftSensorBottom, liftSensorTop;
    public void init(HardwareMap hwMap){
        liftMotor = hwMap.get(DcMotor.class,"lift_motor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftSensorBottom = hwMap.get(RevTouchSensor.class,"lift_sensor_bottom");
        liftSensorTop = hwMap.get(RevTouchSensor.class,"lift_sensor_top");
    }

    public void setLiftMotorPower(double power){
        //positive values move the robot up, negative move it down.
        liftMotor.setPower(power);
    }

    public double getLiftMotorPower(){
        return liftMotor.getPower();
    }

    public boolean getLiftSensorBottom() {return liftSensorBottom.isPressed() ;}

    public boolean getLiftSensorTop() {return liftSensorTop.isPressed() ;}
}
