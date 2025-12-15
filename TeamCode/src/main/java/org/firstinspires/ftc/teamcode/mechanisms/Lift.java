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
        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftSensorBottom = hwMap.get(RevTouchSensor.class,"lift_sensor_bottom");
        liftSensorTop = hwMap.get(RevTouchSensor.class,"lift_sensor_top");
    }

    public void setLiftMotorPower(double power){
        liftMotor.setPower(power);
    }

    public double getLiftMotorPower(){
        return liftMotor.getPower();
    }

    public RevTouchSensor getLiftSensorBottom() {return liftSensorBottom;boolean    }
}
