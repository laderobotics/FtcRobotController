package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor intakeMotor; //This is the motor that spins the rubber band intake

    private CRServo rollerServo; //This is the servo turning the counter-rotating bar at the back of the intake

    public void init(HardwareMap hwMap){
        //intake motor initialization
        intakeMotor = hwMap.get(DcMotor.class,"intake_motor"); //Control Hub motor 0
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Control using power instead of speed
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //Stop when button isn't pressed
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD); //Switch to reverse if spinning wrong way
        //roller servo initialization
        rollerServo = hwMap.get(CRServo.class,"roller_servo"); //Control Hub servo 0
        rollerServo.setDirection(DcMotorSimple.Direction.FORWARD); //Switch to reverse if spinning wrong way
    }

    //intake motor methods
    public void setIntakeMotorPower(double power) { //setter
        intakeMotor.setPower(power);
    }
    public double getIntakeMotorPower() { //getter
        return intakeMotor.getPower();
    }

    //roller servo methods
    public void setRollerServoPower(double power) { //setter
        rollerServo.setPower(power);
    }
    public double getRollerServoPower() { //getter
        return rollerServo.getPower();
    }
}
