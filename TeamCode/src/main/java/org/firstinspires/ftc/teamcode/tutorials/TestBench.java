package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class TestBench {
    private DigitalChannel touchSensor; //for real, name according to job on robot.  Ex: touchSensorIntake

    private DcMotor motor; //for real, name according to job on robot. Ex: motorIntake
    private double ticksPerRev;

    private DistanceSensor distance;

    private Servo servoPos;
    private CRServo servoCR;

    private NormalizedColorSensor colorSensor;
    public enum DetectedColor {
        RED,
        BLUE,
        YELLOW,
        UNKNOWN
    }

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
        //Distance Sensor
        distance = hwMap.get(DistanceSensor.class,"distance_sensor");
        //Positional Servo
        servoPos = hwMap.get(Servo.class,"servo_pos");
        servoPos.scaleRange(0.5,1.0); // set range from midpoint to 180deg
        servoPos.setDirection(Servo.Direction.REVERSE); //set the direction of the servo movement
        //Continuous Rotational Servo
        servoCR = hwMap.get(CRServo.class,"servo_cr");
        servoCR.setDirection(DcMotorSimple.Direction.REVERSE); //set the direction of the servo movement
        //Color Sensor
        colorSensor = hwMap.get(NormalizedColorSensor.class,"color_sensor_distance");
        colorSensor.setGain(8); //change gain to get good readings for sample colors
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

    public double getDistance(){
        return distance.getDistance(DistanceUnit.CM);
    }

    public void setServoPos(double angle) {
        servoPos.setPosition(angle);
    }
    public double getServoPos(){
        return servoPos.getPosition();
    }

    public void setServoCRPower(double power) {
        servoCR.setPower(power);
    }

    public DetectedColor getDetectedColor(Telemetry telemetry){
        NormalizedRGBA colors = colorSensor.getNormalizedColors(); // return 4 values (decimal rep of RGBA)
        float normRed, normGreen, normBlue;
        normRed = colors.red/colors.alpha;
        normGreen = colors.green/colors.alpha;
        normBlue = colors.blue/colors.alpha;

        telemetry.addData("Red",normRed);
        telemetry.addData("Green",normGreen);
        telemetry.addData("Blue",normBlue);

        /*
        red, green, blue
        RED = >0.35, <0.3, <0.3
        YELLOW = >0.5, >0.9, <0.6
        BLUE = <0.2, <0.5, >0.5
         */

        if(normRed>0.35 && normGreen<0.3 && normBlue<0.3){
            return DetectedColor.RED;
        }
        else if(normRed>0.5 && normGreen>0.9 && normBlue<0.6){
            return DetectedColor.YELLOW;
        }
        else if(normRed<0.2 && normGreen<0.5 && normBlue>0.5){
            return DetectedColor.BLUE;
        }
        else{
            return DetectedColor.UNKNOWN;
        }
    }
}
