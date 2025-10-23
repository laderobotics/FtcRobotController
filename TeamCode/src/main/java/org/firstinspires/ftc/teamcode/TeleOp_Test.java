//TODO: This is my note.
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Main TeleOp", group = "robot")
public class TeleOp_Test extends LinearOpMode {
    int myVariable = 2;
    double myDouble = 4.221312;
    boolean myBoolean = true;
    Servo myServo;
    DcMotor myMotor;

    private void reallyNiceCode() {
        if(gamepad1.a) {
            myVariable = 3;
            myServo.setPosition(0.8);
        }
        if(gamepad1.b) {
            myVariable = -2;
            myServo.setPosition(0.2);
        }
    }

    private void telemetry() {
        telemetry.addData("myVariable is equal to ", myVariable);
        telemetry.addData("myServo is equal to", myServo.getPosition());
        telemetry.update();
    }

    private void initializeHardware() {
        myServo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void runOpMode() {
        //initialization code
        initializeHardware();

        waitForStart();

        while(opModeIsActive()) {
            reallyNiceCode();
            telemetry();
        }
    }
}