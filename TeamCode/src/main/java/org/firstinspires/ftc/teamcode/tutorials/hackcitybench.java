package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class hackcitybench {

    private DcMotor motor; // LinearSlideMotor0;
    private double  ticksPerRev; // revolution
    public void init(HardwareMap hmMap){
        //touch sensor code

        // Dc motor
        motor = hmMap.get(DcMotor.class,"motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();
    }

    public void setMotorSpeed(double speed){
        //acceptts value from -1.0 = 1.0
        motor.setPower(speed);
    }


    public double getMotorRevs (){
        return motor.getCurrentPosition() / ticksPerRev *2; // normalizing ticks to revovultions 2:1
    }
}
