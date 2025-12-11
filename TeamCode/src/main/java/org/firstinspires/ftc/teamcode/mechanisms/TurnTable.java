package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TurnTable {

    private CRServo turnServo; //This is the servo that turns the turntable
    private TouchSensor magSensor; //This is the sensor that tells the table when to stop turning

    public void init(HardwareMap hwMap) {
        //turn servo initialization
        turnServo = hwMap.get(CRServo.class, "turn_table_servo"); // Control Hub servo 1
        magSensor = hwMap.get(TouchSensor.class, "turn_table_mag_sensor"); // Control Hub digital 1?
    }

    public void setTurnServoPower(double power){
        turnServo.setPower(power);
    }

    public boolean isMagnetPresent(){
        return magSensor.isPressed();
    }
}
