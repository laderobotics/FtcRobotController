package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.TurnTable;



@TeleOp
public class FrenchFryTeleop extends OpMode {

    Intake intake = new Intake();
    TurnTable turnTable = new TurnTable();
    Launcher launcher=new Launcher();
    MecanumDrive drive= new MecanumDrive();
    boolean magSensed =false;
    double turnDirection = 1.0;
    double cannonSpeedMultiplier=0.8;
    double launchPosition = 0.0;
    double safePosition=0.33;
    boolean safeToTurn = false;
    double horizontal, vertical, rotate;
    double slowTrigger=0;

    boolean atSpeed = false;
    boolean isSpinningUp = false;


    @Override
    public void init() {
        intake.init(hardwareMap);
        turnTable.init(hardwareMap);
        launcher.init(hardwareMap);
        drive.init(hardwareMap);
        launcher.setLiftToLaunchServoPosition(safePosition);

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

        telemetry.addData("Mag Sensor",turnTable.isMagnetPresent());

        if(turnTable.isMagnetPresent()){
            if(gamepad2.rightBumperWasPressed()&&safeToTurn){
                turnTable.setTurnServoPower(1.0);
                turnDirection = 1.0;
            }
            else if (gamepad2.leftBumperWasPressed()&&safeToTurn) {
                turnTable.setTurnServoPower(-1.0);
                turnDirection = -1.0;
            }
            else if (gamepad2.rightBumperWasReleased()) {
                turnTable.setTurnServoPower(0.0);
                turnDirection *= -1;
            }
            else if (gamepad2.leftBumperWasReleased()) {
                turnTable.setTurnServoPower(0.0);
                turnDirection *= -1;
            }
        }
        else{
            turnTable.setTurnServoPower(turnDirection);
        }
        /* Competition 1 turntable code
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
        } */

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
        drive.drive(vertical,horizontal,rotate,slowTrigger);

        if(gamepad1.y){
            drive.resetYaw();
        }
    }
}
