package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBench {
    private DigitalChannel touchSensor; //for real, name according to job on robot.  Ex: touchSensorIntake

    private DcMotor motor; //for real, name according to job on robot. Ex: motorIntake
    private double ticksPerRev;

    public void init(HardwareMap hwMap){
        //Touch Sensor
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        // DC Motor
        motor = hwMap.get(DcMotor.class,"intake_motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        ticksPerRev = motor.getMotorType().getTicksPerRev();
    }

    public boolean isTouchSensorPressed(){
        return !touchSensor.getState();
    }
    public boolean isTouchSensorReleased(){
        return touchSensor.getState();
    }

    public void setMotorSpeed(double speed){
        //Accepts values from -1.0 to 1.0
        motor.setPower(speed);
    }

    public double getMotorRevs(){
        return motor.getCurrentPosition() / ticksPerRev; // Normalizing ticks to revolutions
        //Note: if you have a gear ratio on the motor, multiply this by the gear ratio
        //Ex: for a 19.1:1 gear ratio motor: motor.getCurrentPosition() / ticksPerRev * 19.1
    }

    public void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

}
