package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.SonicSubsystemBase;


public class Virtual4Bar extends SonicSubsystemBase {

    private Servo servo;

    private Telemetry telemetry;

    public Virtual4Bar(HardwareMap hardwareMap, Telemetry telemetry) {
        /* instantiate motors */
        this.servo = hardwareMap.get(Servo.class,"V4Bar");
        this.telemetry = telemetry;
        MoveServo();
    }

    int state = 0;

    public void Raise() {
        if(state < 2) {
            state++;
            MoveServo();
        }
    }

    public void Lower() {
        if(state > 0) {
            state--;
            MoveServo();
        }
    }

    private void MoveServo() {
        double position;
        switch (state) {
            case 0:
                position = 1;
                break;
            case 1:
                position = 0.4;
                break;
            default:
                position = 0;
        }

        telemetry.addData("state", state);
        telemetry.addData("position", position);
        telemetry.update();
        this.servo.setPosition(position);
    }
}