package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Launcher {

    private DcMotorEx flywheelMotor; //This is the motor that spins up and launches the artifact ball.
    private Servo ballLifterServo; //This is the servo with the silver claw that lifts the ball
                                    // from the turntable into the flywheel launcher

    double startingSmallZoneSpeed = 1300;//Starting point for flywheel speed for big launch zone near goal
    double smallZoneSpeed = startingSmallZoneSpeed;
    double startingBigZoneSpeed = 1680; //Starting point for flywheel speed for small launch zone far from goal
    double bigZoneSpeed = startingBigZoneSpeed;
    double[] stepSizes = {10,10};
    double m = 6.09952;
    double b = 967.57624;
    double startingB=b;
    int stepIndex = 0;
    boolean bigZone = true;

    public void init(HardwareMap hwMap){
        //flywheel motor initialization
        flywheelMotor = hwMap.get(DcMotorEx.class, "flywheel_motor"); //Control hub motor 2
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not power
        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //Keep spinning with momentum
        flywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE); //Switch to reverse if spinning wrong way
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(70.0,0,0,12.0);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        //lift to launch servo initialization
        ballLifterServo = hwMap.get(Servo.class,"ball_lifter_servo"); //Control hub servo 2
        ballLifterServo.setDirection(Servo.Direction.REVERSE); //Switch to reverse if turning wrong way
        ballLifterServo.scaleRange(0.0, 1.0); //Replace with min position and max position
    }

    //Flywheel methods
    public void setVelocity(double velocity){ //setter RUN_USING_ENCODER mode, in ticks per second
        flywheelMotor.setVelocity(velocity);
    }
    public double getCurrentVelocity() { // getter RUN_USING_ENCODER mode, in ticks per second
        return flywheelMotor.getVelocity();
    }

    public void setMotorPower(double power){ //setter RUN_WITHOUT_ENCODER mode
        flywheelMotor.setPower(power);
    }
    public double getMotorPower() { //getter RUN_WITHOUT_ENCODER mode
        return flywheelMotor.getPower();
    }

    public void updateFlywheel(boolean incButton,
                               boolean decButton,
                               boolean stepButton,
                               boolean bigZoneButton,
                               boolean smallZoneButton,
                               boolean resetButton,
                               double range,
                               Telemetry telemetry) {
        //handles changing between big/small launch zone speeds, adjusting speeds on the fly with the d-pad
//        if(smallZoneButton){
//            bigZone = false;
//        }
//        if(bigZoneButton){
//            bigZone = true;
//        }


        if(resetButton){
            b=startingB;
//            if(bigZone){ bigZoneSpeed = startingBigZoneSpeed; }
//            else{ smallZoneSpeed = startingSmallZoneSpeed; }
        }
        double currentTargetSpeed;
        currentTargetSpeed = m*range+b;
//        if(bigZone){
//            currentTargetSpeed = bigZoneSpeed;
//        }
//        else{
//            currentTargetSpeed = smallZoneSpeed;
//        }
//        if(stepButton){
//            stepIndex = (stepIndex + 1) % stepSizes.length;
//        }
        if(incButton){
            b += 10;
        }
        if(decButton){
            b -= 10;
        }
//        if(bigZone){
//            bigZoneSpeed = currentTargetSpeed;
//        }
//        else{
//            smallZoneSpeed = currentTargetSpeed;
//        }
        setVelocity(currentTargetSpeed);
        telemetry.addData("target speed", currentTargetSpeed);
        telemetry.addData("actual speed",getCurrentVelocity());
        telemetry.addData("step size",stepSizes[stepIndex]);
    }


    //Lift to launch servo methods
    public void setLauncherServoPosition(double angle) { //setter
        ballLifterServo.setPosition(angle);
    }
    public double getLauncherServoPosition() { //getter
        return ballLifterServo.getPosition();
    }
}
