package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.TurnTable;

//TODO Calibrate F and P values to switch between speeds and return to speed optimally.
//TODO Use PIDF coefficients in this opMode like in the Flywheel tuner opMode.

@TeleOp
public class FrenchFryTeleop extends OpMode {

    Intake intake = new Intake();
    TurnTable turnTable = new TurnTable();
    Launcher launcher=new Launcher();
    MecanumDrive drive= new MecanumDrive();
    Lift lift=new Lift();
    boolean turntableManual = false; //false = auto turn to magnet with bumpers,
                                   // true = manual control with triggers
    boolean cwButton, ccwButton; //buttons for turning the turn table automatically
    double cwPower, ccwPower; //triggers for turning the turn table manually
    boolean magPresent; //true when the magnet is present, aka, when turn table position is correct
    double cannonSpeedMultiplier=0.8;
    double launchPosition = 0.0;
    double safePosition=0.33;
    boolean safeToTurn = false;
    double horizontal, vertical, rotate;
    double slowTrigger=0;
    boolean driveFieldRelative = true; //start in Field Relative Driving mode

    boolean atSpeed = false;
    boolean isSpinningUp = false;


    @Override
    public void init() {
        intake.init(hardwareMap);
        turnTable.init(hardwareMap);
        launcher.init(hardwareMap);
        drive.init(hardwareMap);
        launcher.setLiftToLaunchServoPosition(safePosition);
        lift.init(hardwareMap);

    }


    @Override
    public void loop() {
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
        launcher.setFlywheelMotorSpeed(0.2+(cannonSpeedMultiplier*gamepad2.right_trigger));
        if (gamepad2.dpad_up){
            launcher.setLiftToLaunchServoPosition(launchPosition);
            safeToTurn=false;
        }
        else{
            launcher.setLiftToLaunchServoPosition(safePosition);
            safeToTurn=true;
        }
        //New Turntable code to test
        if(turntableManual){ //Use the triggers to spin the turntable manually, ignoring the mag sensor
            turnTable.setTurnServoPower(0.5*(cwPower - ccwPower));
        }
        else{ //Use the bumpers to turn the turntable to the next magnet, self corrects to the left
            turnTable.updateTurnTable(safeToTurn&&cwButton,safeToTurn&&ccwButton, telemetry);
        }

        /* Competition 1 Turntable code, test new code before deleting!
        if (gamepad2.left_bumper) {
           turnTable.setTurnServoPower(1.0);//You shall pass!
                }
        else if (gamepad2.right_bumper) {
            if (turnTable.isMagnetPresent()) {
                turnTable.setTurnServoPower(0.0);
            }
            else {
                turnTable.setTurnServoPower(-1.0);
                }
            }
        else {
            turnTable.setTurnServoPower(0.0);
        }*/

        telemetry.addData("Mag Sensor",magPresent);


        telemetry.addData("right trigger",gamepad2.right_trigger);
        telemetry.addData("power",launcher.getMotorPower());
        telemetry.addData("velocity", launcher.getCurrentVelocity());


        launcher.setFlywheelMotorSpeed(0.2+(cannonSpeedMultiplier*gamepad2.right_trigger));
        if (gamepad2.dpad_up){
            launcher.setLiftToLaunchServoPosition(launchPosition);
            safeToTurn=false;
        }
        else{
            launcher.setLiftToLaunchServoPosition(safePosition);
            safeToTurn=true;
        }


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
            if(gamepad1.y){
                drive.resetYaw();
            }
        }
        else{
            drive.drive(vertical,horizontal,rotate,slowTrigger);
        }

        //Lift Stuff XD
        if (gamepad1.dpad_up){
            if(!lift.getLiftSensorTop()){
                lift.setLiftMotorPower(1.0);
            }
            else {
                lift.setLiftMotorPower(0.0);
            }
        }
        else {
            if (!lift.getLiftSensorBottom()){
                lift.setLiftMotorPower(-1.0);
            }
            else {
                lift.setLiftMotorPower(0.0);
            }
        }
        telemetry.addData("Motor Power",lift.getLiftMotorPower());
        telemetry.addData("Top Sensor",lift.getLiftSensorTop());
        telemetry.addData("Bottom Sensor",lift.getLiftSensorBottom());


    }
}
