package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.SonicSubsystemBase;
import org.firstinspires.ftc.teamcode.subsystems.feedback.DriverFeedback;


public class IntakeSlider extends SonicSubsystemBase {

    private CRServo servo1;

    private Telemetry telemetry;

    public IntakeSlider(HardwareMap hardwareMap, Telemetry telemetry) {
        /* instantiate motors */
        this.servo1  = hardwareMap.get(CRServo.class,"IntakeServos");

        this.telemetry = telemetry;
    }

    boolean isExpanding = false;

    public void ToggleExpand() {
        if(isExpanding) {
            Hold();
        } else {
            Expand();
        }

        isExpanding = !isExpanding;
    }

    boolean isCollapsing = false;

    public void ToggleCollapse() {
        if(isCollapsing) {
            Hold();
        } else {
            Collapse();
        }

        isCollapsing = !isCollapsing;
    }

    public void Expand() {
        this.servo1.setPower(1);
    }

    public void Collapse() {
        this.servo1.setPower(-1);
    }

    public void Hold() {
        this.servo1.setPower(0.0);
    }

    public void AutoExpand() {
        this.servo1.setPower(-1);

        this.servo1.setPower(0);
    }
}