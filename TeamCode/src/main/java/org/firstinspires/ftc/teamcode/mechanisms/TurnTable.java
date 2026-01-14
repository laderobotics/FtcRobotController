package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TurnTable {

    private CRServo turnServo; //This is the servo that turns the turntable
    private TouchSensor magSensor; //This is the sensor that tells the table when to stop turning

    private enum TurntableState {
        IDLE,
        MOVING_OFF_MAGNET,
        SEARCHING_FOR_NEXT_MAGNET,
        RETURNING_TO_MAGNET
    }

    TurntableState state = TurntableState.IDLE;

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
    public void updateTurnTable(boolean cwButton, boolean ccwButton){
        boolean magnetDetected = magSensor.isPressed();
        int turnDirection = 1;
        double turnPower = 1.0;

        switch (state){
            case IDLE:
                //Driver 2 command -> Go to next magnet
                if(cwButton){
                    turnServo.setPower(turnPower);
                    state = TurntableState.MOVING_OFF_MAGNET;
                    turnDirection = 1;
                }
                else if(ccwButton){
                    turnServo.setPower(-turnPower);
                    state = TurntableState.MOVING_OFF_MAGNET;
                    turnDirection = -1;
                }
                //Drift Detected, reverse
                else if(!magnetDetected){
                    turnServo.setPower(-1*turnDirection*turnPower);
                    state = TurntableState.RETURNING_TO_MAGNET;
                }
                break;

            case MOVING_OFF_MAGNET:
                //Wait until we leave the current magnet
                if(!magnetDetected){
                    turnServo.setPower(turnDirection*turnPower);
                    state = TurntableState.SEARCHING_FOR_NEXT_MAGNET;
                }
                break;

            case SEARCHING_FOR_NEXT_MAGNET:
                //Stop on next magnet
                if(magnetDetected){
                    turnServo.setPower(0);
                    state = TurntableState.IDLE;
                }
                break;

            case RETURNING_TO_MAGNET:
                //Re-center on magnet
                if(magnetDetected){
                    turnServo.setPower(0);
                    state = TurntableState.IDLE;
                }
                break;
        }
    }

}
