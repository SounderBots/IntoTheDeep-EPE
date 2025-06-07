package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.SonicSubsystemBase;


public class Turret extends SonicSubsystemBase {

    private Servo servo;

    private Telemetry telemetry;

    public Turret(HardwareMap hardwareMap, Telemetry telemetry) {
        /* instantiate motors */
        this.servo = hardwareMap.get(Servo.class,"Turret");
        this.telemetry = telemetry;
    }

    public void TurnLeft() {

        this.servo.setPosition(0.0);
    }

    public void TurnRight() {
        this.servo.setPosition(0.5);

    }
}