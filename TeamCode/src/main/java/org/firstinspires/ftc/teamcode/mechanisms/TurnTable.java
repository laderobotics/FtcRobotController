package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TurnTable {

    private CRServo turnServo; //This is the servo that turns the turntable
    private TouchSensor magSensor; //This is the sensor that tells the table when to stop turning

    public enum TurntableState {
        IDLE,
        MOVING_OFF_MAGNET,
        SEARCHING_FOR_MAGNET,
        ERROR
    }

    TurntableState state = TurntableState.IDLE;
    int turnDirection;
    double turnPower;

    public void init(HardwareMap hwMap) {
        //turn servo initialization
        turnServo = hwMap.get(CRServo.class, "turn_table_servo"); // Control Hub servo 1
        magSensor = hwMap.get(TouchSensor.class, "turn_table_mag_sensor"); // Control Hub digital 1
    }

    public void setTurnServoPower(double power){
        turnServo.setPower(power);
    }

    public boolean isMagnetPresent(){
        return magSensor.isPressed();
    }

    //With the help of ChatGPT I made a state machine for the turntable!  I adjusted it to use both bumpers
    // so we can turn the turntable either direction.  Needs testing!
    public TurntableState updateTurnTable(boolean cwButton, boolean ccwButton, Telemetry telemetry){
        boolean magnetDetected = magSensor.isPressed();
        telemetry.addData("Turntable State",state);
        switch (state){
            case IDLE:
                //Driver 2 command -> Go to next magnet
                if(cwButton){
                    turnServo.setPower(0.4);
                    turnPower = 0.4;
                    turnDirection = 1;
                    state = TurntableState.MOVING_OFF_MAGNET;
                }
                else if(ccwButton){
                    turnServo.setPower(-0.5);
                    turnPower = 0.5;
                    turnDirection = -1;
                    state = TurntableState.MOVING_OFF_MAGNET;
                }
                //Drift Detected, reverse
                //(only corrects by pushing left, to correct for a launch pushing it out of position.)
                else if(!magnetDetected){
                    turnServo.setPower(-0.1);
                    state = TurntableState.SEARCHING_FOR_MAGNET;
                }
                return TurntableState.IDLE ;

            case MOVING_OFF_MAGNET:
                //Wait until we leave the current magnet
                if(!magnetDetected){
                    turnServo.setPower(turnDirection*turnPower);
                    state = TurntableState.SEARCHING_FOR_MAGNET;
                }
                return TurntableState.MOVING_OFF_MAGNET ;

            case SEARCHING_FOR_MAGNET:
                //Stop on next magnet - slow as you approach
                if(turnPower>0.1){
                    turnServo.setPower(turnDirection*turnPower);
                    turnPower -= 0.05; //adjust this number until turntable slows enough to catch next magnet
                }
                if(magnetDetected){
                    turnServo.setPower(0);
                    state = TurntableState.IDLE;
                }
                return TurntableState.SEARCHING_FOR_MAGNET ;
        }

        return TurntableState.ERROR;
    }

}
