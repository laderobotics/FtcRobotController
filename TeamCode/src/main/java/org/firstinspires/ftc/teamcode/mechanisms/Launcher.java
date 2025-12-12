package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {

    private DcMotor flywheelMotor; //This is the motor that spins up and launches the artifact ball.
    private Servo ballLifterServo; //This is the servo with the silver claw that lifts the ball
                                    // from the turntable into the flywheel launcher



    public void init(HardwareMap hwMap){
        //flywheel motor initialization
        flywheelMotor = hwMap.get(DcMotor.class, "flywheel_motor"); //Control hub motor 2
        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //Keep spinning with momentum
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD); //Switch to reverse if spinning wrong way
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not power

        //lift to launch servo initialization
        ballLifterServo = hwMap.get(Servo.class,"ball_lifter_servo"); //Control hub servo 2
        ballLifterServo.setDirection(Servo.Direction.FORWARD); //Switch to reverse if turning wrong way
        ballLifterServo.scaleRange(0.0, 1.0); //Replace with min position and max position
    }

    //Flywheel methods
    public void setFlywheelMotorSpeed(double speed){ //setter
        flywheelMotor.setPower(speed);
    }
    public double getFlywheelMotorSpeed() { //getter
        return flywheelMotor.getPower();
    }

    //Lift to launch servo methods
    public void setLiftToLaunchServoPosition(double angle) { //setter
        ballLifterServo.setPosition(angle);
    }
    public double getLiftToLaunchServoPosition() { //getter
        return ballLifterServo.getPosition();
    }
}
