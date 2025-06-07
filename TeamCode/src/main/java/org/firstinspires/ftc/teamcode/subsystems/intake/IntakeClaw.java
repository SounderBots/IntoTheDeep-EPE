package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.SonicSubsystemBase;


public class IntakeClaw extends SonicSubsystemBase {

    private Servo servo;

    private Telemetry telemetry;

    public IntakeClaw(HardwareMap hardwareMap, Telemetry telemetry) {
        /* instantiate motors */
        this.servo = hardwareMap.get(Servo.class,"Claw");
        this.telemetry = telemetry;
    }

    public void Grab() {
        this.servo.setPosition(1);
    }

    public void Release() {

        this.servo.setPosition(0);
    }
}