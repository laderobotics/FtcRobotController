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
    boolean magFlag =false;
    double cannonSpeedMultiplier=0.8;
    double launchPosition = 0.1;


    double safePosition=0.0;
    boolean safeToTurn = false;
    double horizontal, vertical, rotate;
    double slowTrigger;


    @Override
    public void init() {
        intake.init(hardwareMap);
        turnTable.init(hardwareMap);
        launcher.init(hardwareMap);
        drive.init(hardwareMap);

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
        if (turnTable.isMagnetPresent()&&!magFlag) {
            magFlag = true;
        }
        if (!turnTable.isMagnetPresent()){
            magFlag=false;
        }




        if (gamepad2.left_bumper){
            if (turnTable.isMagnetPresent()){
                if (magFlag&&safeToTurn){
                    turnTable.setTurnServoPower(1.0);
                }
                else {
                    turnTable.setTurnServoPower(0.0);
                }
            }
            else {
                if (safeToTurn){
                turnTable.setTurnServoPower(1.0);
            }

        }
            if (gamepad2.right_bumper) {
                if (turnTable.isMagnetPresent()) {
                    if (magFlag && safeToTurn) {
                        turnTable.setTurnServoPower(-1.0);
                    } else {
                        turnTable.setTurnServoPower(0.0);
                    }
                } else {
                    if (safeToTurn) {
                        turnTable.setTurnServoPower(-1.0);
                    }

                }
            }
        else{
            turnTable.setTurnServoPower(0.0);



        }

        launcher.setFlywheelMotorSpeed(0.2+cannonSpeedMultiplier*gamepad1.right_trigger);


        if (gamepad2.dpad_up){
            launcher.setLiftToLaunchServoPosition(launchPosition);
            safeToTurn=false;


        }
        else{
            launcher.setLiftToLaunchServoPosition(safePosition);
            safeToTurn=true;

        }
        horizontal= gamepad1.left_stick_x;
        vertical= gamepad1.left_stick_y;
        rotate= gamepad1.right_stick_x;
        slowTrigger= gamepad1.right_trigger;
        drive.driveFieldRelative(horizontal,vertical,rotate,slowTrigger);
    }
    }
}
