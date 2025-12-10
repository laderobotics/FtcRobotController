package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TurnTable {

    private CRServo turnServo;
    private TouchSensor magSensor;

    public void init(HardwareMap hwMap) {
        turnServo = hwMap.get(CRServo.class, "turn_table_servo");
        magSensor = hwMap.get(TouchSensor.class, "turn_table_mag_sensor");
    }

    public void setTurnServoPower(double power){
        turnServo.setPower(power);
    }

    public boolean isMagnetPresent(){
        return magSensor.isPressed();
    }
}
