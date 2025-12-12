package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.TurnTable;

@TeleOp
public class FrenchFryTeleop extends OpMode {

    Intake intake = new Intake();
    //TurnTable turnTable = new TurnTable();
    @Override
    public void init() {
        intake.init(hardwareMap);
        //turnTable.init(hardwareMap);

    }


    @Override
    public void loop() {
        if (gamepad2.a){
            intake.setIntakeMotorPower(1.0);
            intake.setRollerServoPower(1.0);

        } else if (gamepad2.b) {
            intake.setIntakeMotorPower(-1.0);
            intake.setRollerServoPower(-1.0);

        } else {
            intake.setIntakeMotorPower(0.0);
            intake.setRollerServoPower(0.0);

        }
    }
}
