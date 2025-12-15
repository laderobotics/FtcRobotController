package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {

    private DcMotor frontLeftMotor, backLeftMotor,frontRightMotor, backRightMotor; //These are the four
    //motors that drive around the robot, according to where they are on the robot
    private IMU imu; //imu = internal measurement unit - use this to measure which angle the robot is facing

    public void init(HardwareMap hwMap){
        frontLeftMotor = hwMap.get(DcMotor.class,"front_left_motor"); //Expansion hub motor 0
        backLeftMotor = hwMap.get(DcMotor.class,"back_left_motor"); //Expansion hub motor 1
        frontRightMotor = hwMap.get(DcMotor.class,"front_right_motor"); //Expansion hub motor 2
        backRightMotor = hwMap.get(DcMotor.class,"back_right_motor"); //Expansion hub motor 3

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE); // reverse direction to correct this motor
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD); // reverse direction to correct this motor

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not just power
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not just power
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not just power
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Control using speed not just power

        imu = hwMap.get(IMU.class,"imu"); // Built into the control hub, don't change its name
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot( // change to match how the control hub is mounted
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD, //Which way is the "Control Hub" logo facing?
                RevHubOrientationOnRobot.UsbFacingDirection.UP); //Which way are the USB ports facing?
        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    // Drives according to robot orientation (forward on stick = forward from robot perspective)
    public void drive(double forward, double strafe, double rotate,double maxSpeed){ // Drives according to robot orientation (forward on stick = forward from robot perspective)
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        double mult = 1.0 - 0.75 * maxSpeed;


        maxPower = Math.max(maxPower,Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower,Math.abs(backLeftPower));
        maxPower = Math.max(maxPower,Math.abs(frontRightPower));
        maxPower = Math.max(maxPower,Math.abs(backRightPower));

        frontLeftMotor.setPower(mult * frontLeftPower/maxPower);
        backLeftMotor.setPower(mult * backLeftPower/maxPower);
        frontRightMotor.setPower(mult * frontRightPower/maxPower);
        backRightMotor.setPower(mult * backRightPower/maxPower);
    }

    //Drives according to field orientation (forward on stick = forward from driver orientation)
    public void driveFieldRelative(double forward, double strafe, double rotate,double maxSpeed){
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate,maxSpeed);
    }

    public void resetYaw(){
        imu.resetYaw();
    }
}
