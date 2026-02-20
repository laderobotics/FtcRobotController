package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.RevLEDIndicator;
import org.firstinspires.ftc.teamcode.mechanisms.TurnTable;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp
public class BlueTeleop extends OpMode {

    Intake intake = new Intake();
    TurnTable turnTable = new TurnTable();
    Launcher launcher=new Launcher();
    MecanumDrive drive= new MecanumDrive();
    Lift lift=new Lift();

    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();

    RevLEDIndicator led = new RevLEDIndicator();
    boolean turntableManual = false; //false = auto turn to magnet with bumpers,
                                   // true = manual control with triggers
    boolean cwButton, ccwButton; //buttons for turning the turn table automatically
    double cwPower, ccwPower; //triggers for turning the turn table manually
    boolean magPresent; //true when the magnet is present, aka, when turn table position is correct
    double launchPosition = 0.0; //launcher servo position to launch an artifact
    double safePosition=0.33; //launcher servo position to be safely out of the way of the turntable
    boolean safeToTurn = false; //Used to prevent turntable from turning if the launcher servo is in the way
    double horizontal, vertical, rotate; //read from the control sticks to drive the wheels
    double slowTrigger=0; //Used to slow the robot down to a minimum 0f 25% (fully pressed)
    boolean driveFieldRelative = false; //start in Robot Relative Driving mode


    double range;
    double prevRange=120;

    double launchSpreadAngle = 0.5;
    double launchAngleOffset = -5;


    @Override
    public void init() {
        intake.init(hardwareMap);
        turnTable.init(hardwareMap);
        launcher.init(hardwareMap);
        drive.init(hardwareMap);
        launcher.setLauncherServoPosition(safePosition);
        aprilTagWebcam.init(hardwareMap, telemetry);
        lift.init(hardwareMap);
        led.init(hardwareMap);


    }


    @Override
    public void loop() {
        //update the vision portal
        aprilTagWebcam.update();
        AprilTagDetection id20 = aprilTagWebcam.getTagBySpecificId(20);
        aprilTagWebcam.displayDetectionTelemetry(id20);
        range = aprilTagWebcam.getDetectionRange(id20);

        if (range==-1){
            range=prevRange;
        }
        else {

            prevRange=range;
        }
        double angle = aprilTagWebcam.getDetectionAngle(id20);
        telemetry.addData("range",range);
        //Intake control
        if (gamepad2.a){
            intake.setIntakeMotorPower(1.0);
            intake.setRollerServoPower(1.0);

        }
        else if (gamepad2.b) {
            intake.setIntakeMotorPower(-1.0);
            intake.setRollerServoPower(-1.0);

        }
        else {
            intake.setIntakeMotorPower(0.0);
            intake.setRollerServoPower(0.0);

        }

        //Turntable control
        cwButton = gamepad2.left_bumper;
        ccwButton = gamepad2.right_bumper;
        cwPower = gamepad2.left_trigger;
        ccwPower = gamepad2.right_trigger;
        magPresent = turnTable.isMagnetPresent();

        if(cwButton || ccwButton){
            turntableManual = false;
        }
        else if(cwPower>0.1 || ccwPower>0.1){
            turntableManual = true;
        }

        //Launch servo control
        if (gamepad2.dpad_up){
            if (angle<=(launchSpreadAngle+launchAngleOffset) && angle>=(-launchSpreadAngle+launchAngleOffset)){
                launcher.setLauncherServoPosition(launchPosition);
                safeToTurn=false;
            } else if (angle == 180) {
                launcher.setLauncherServoPosition(launchPosition);
                safeToTurn=false;
            }
            else { //Auto-Aim!  If robot can see the april tag but not pointed well, autocorrect
                if (angle<0+launchAngleOffset){
                    drive.drive(0,0,0.25,0);

                }
                else{
                    drive.drive(0,0,-0.25,0);
                }
            }
        }
        else{
            launcher.setLauncherServoPosition(safePosition);
            safeToTurn=true;
        }

        if (angle<=(launchSpreadAngle+launchAngleOffset)&&angle>=(-launchSpreadAngle+launchAngleOffset)){
            led.setRedLed(true);
            led.setGreenLed(true);
        }
        else if (angle==180.0) {
            led.setGreenLed(false);
            led.setRedLed(true);
        }
        else {
            led.setGreenLed(true);
            led.setRedLed(false);
        }
        //back to turntable control now that we know if it's safe to turn
        if(turntableManual){ //Use the triggers to spin the turntable manually, ignoring the mag sensor
            turnTable.setTurnServoPower(0.25 *(cwPower - ccwPower));
        }
        else{ //Use the bumpers to turn the turntable to the next magnet, self corrects to the left
            turnTable.updateTurnTable(safeToTurn&&cwButton,safeToTurn&&ccwButton, telemetry);
        }
        telemetry.addData("Mag Sensor",magPresent);

        //Flywheel control
        launcher.updateFlywheel(gamepad2.dpadRightWasPressed(), //push to increase current target velocity
                                gamepad2.dpadLeftWasPressed(), //push to decrease current target velocity
                                gamepad2.dpadDownWasPressed(), //push to switch between changing by 100 or 10
                                gamepad2.xWasPressed(), //push to use small zone flywheel speed
                                gamepad2.yWasPressed(), //push to use big zone flywheel speed
                                gamepad2.backWasPressed(), //push to reset to default zone speeds
                                range, //Range to goal (as measured by april tag, in inches)
                                telemetry); //allow flywheel to print telemetry to the screen

        horizontal= gamepad1.left_stick_x;
        vertical= -gamepad1.left_stick_y;
        rotate= gamepad1.right_stick_x;
        slowTrigger= gamepad1.right_trigger;
        //Toggle between Robot Relative and Field Relative driving by pressing X on gamepad1.
        if(gamepad1.xWasPressed()){
            driveFieldRelative = !driveFieldRelative;
        }

        if(driveFieldRelative){
            drive.driveFieldRelative(vertical,horizontal,rotate,slowTrigger);
            telemetry.addLine("*** Field Relative ***");
            if(gamepad1.y){
                drive.resetYaw();
            }
        }
        else{
            drive.drive(vertical,horizontal,rotate,slowTrigger);
            telemetry.addLine("--- Robot Relative ---");
        }

        double velocity = launcher.getCurrentVelocity();
        //Lift control
        if (gamepad1.dpad_up){
            launcher.setVelocity(0);
            lift.raiseLift();
        }
        else if (gamepad1.dpad_down){
            lift.lowerLift();
        }
        telemetry.addData("Motor Power",lift.getLiftMotorPower());
    }
}
