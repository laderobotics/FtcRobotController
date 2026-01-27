package org.firstinspires.ftc.teamcode.tutorials;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;

public class TestBenchLED {
    private LED redLed;
    private LED greenLed;


    public void init(HardwareMap hwMap) {
        redLed = hwMap.get(LED.class, "led_red");
        greenLed = hwMap.get(LED.class, "led_green");
    }

    public  void  setRedLed(boolean isOn){
        if (isOn){
            redLed.on();
        }
        else {
            redLed.off();
        }
    }


    public void setGreenLed (boolean isOn){
        if (isOn){
            greenLed.on();
        }
        else{
            greenLed.off();
        }
    }
}
