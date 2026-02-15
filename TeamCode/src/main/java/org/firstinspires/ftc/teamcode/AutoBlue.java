package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.RevLEDIndicator;
import org.firstinspires.ftc.teamcode.mechanisms.TurnTable;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;


@Autonomous(name="Robot: Auto Blue", group="Robot")
public class AutoBlue extends LinearOpMode{
    Intake intake = new Intake();
    TurnTable turnTable = new TurnTable();
    Launcher launcher=new Launcher();
    MecanumDrive drive= new MecanumDrive();
    Lift lift=new Lift();

    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();

    RevLEDIndicator led = new RevLEDIndicator();
    boolean magPresent; //true when the magnet is present, aka, when turn table position is correct
    double launchPosition = 0.0; //launcher servo position to launch an artifact
    double safePosition=0.33; //launcher servo position to be safely out of the way of the turntable
    boolean safeToTurn = false; //Used to prevent turntable from turning if the launcher servo is in the way
    double range;
    double prevRange=120;

    double launchSpreadAngle = 5;
    int locateCount22=0;
    int locateCount23=0;


    public void runOpMode(){
        intake.init(hardwareMap);
        turnTable.init(hardwareMap);
        launcher.init(hardwareMap);
        drive.init(hardwareMap);
        launcher.setLauncherServoPosition(safePosition);
        aprilTagWebcam.init(hardwareMap, telemetry);
        lift.init(hardwareMap);
        led.init(hardwareMap);
        waitForStart();
        //1)scan obelisk pattern
        List<AprilTagDetection> detectedTags = new ArrayList<>();
        for (int i=0;i<10;i++){
            aprilTagWebcam.update();
            detectedTags=aprilTagWebcam.getDetectedTags();
            for(AprilTagDetection detection : detectedTags){
                if(detection.id == 22){
                    locateCount22++;
                } else if (detection.id == 23) {
                   locateCount23++;

                }
            }
            sleep(20);
        }
        telemetry.addData("22 detections",locateCount22);
        telemetry.addData("23 detections",locateCount23);
        //2)Rotate turn table if needed (Green ball in the launcher)
        sleep(5000);
        if(locateCount22 > 0){
            //if id is 22 turn turn table twice to the left
            turnTable.updateTurnTable(false,true,telemetry);
            while (turnTable.updateTurnTable(false,false,telemetry)!= TurnTable.TurntableState.IDLE){
                //do nothing while the turn table turning
            }
        } else if (locateCount23 > 0) {
            //if id is 24 turn turn table once to the left
            turnTable.updateTurnTable(true,false,telemetry);
            while (turnTable.updateTurnTable(false,false,telemetry)!= TurnTable.TurntableState.IDLE){
                //do nothing while the turn table turning
            }
        }

        //3) Auto aim towards
        //4)fire the artifacts
        //5)turn table repeat 3x
        //6) move out of triangle



    } //67//67//67//67//












}
