package org.firstinspires.ftc.teamcode.opmodes.teleop;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.SounderBotBaseRunCommand;
import org.firstinspires.ftc.teamcode.opmodes.OpModeTemplate;
import org.firstinspires.ftc.teamcode.opmodes.PowerMode;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.command.MovePivotRelativelyCommand;
import org.firstinspires.ftc.teamcode.subsystems.climb.HangingArm;
import org.firstinspires.ftc.teamcode.subsystems.delivery.DeliveryPivot;
import org.firstinspires.ftc.teamcode.subsystems.delivery.DeliverySlider;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.TeleFourWheelMecanumDriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.feedback.DriverFeedback;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeClaw;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSlider;
import org.firstinspires.ftc.teamcode.subsystems.intake.RollingIntake;
import org.firstinspires.ftc.teamcode.subsystems.intake.Turret;
import org.firstinspires.ftc.teamcode.subsystems.intake.Virtual4Bar;
import org.firstinspires.ftc.teamcode.subsystems.specimen.SpecimenSlider;
import org.firstinspires.ftc.teamcode.subsystems.specimen.SpecimenSliderClaw;

/**
 * This is old mainTeleop
 */
@TeleOp
@SuppressWarnings("unused")
public class MainTeleop_Euro extends OpModeTemplate {

    private static final String LOG_TAG = MainTeleop_Euro.class.getSimpleName();

    @Override
    public void initialize() {
        super.initialize();

        IntakeSlider intakeSlider = new IntakeSlider(hardwareMap, telemetry);
        Virtual4Bar virtual4Bar = new Virtual4Bar(hardwareMap, telemetry);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap, telemetry);
        Turret turret = new Turret(hardwareMap, telemetry);

        // Robot direction
        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenHeld(new InstantCommand(intakeSlider::Expand, intakeSlider))
                .whenReleased(new InstantCommand(intakeSlider::Hold, intakeSlider));

        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(new InstantCommand(intakeSlider::Collapse, intakeSlider))
                .whenReleased(new InstantCommand(intakeSlider::Hold, intakeSlider));

        operatorGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(new InstantCommand(intakeSlider::Collapse, intakeSlider))
                .whenReleased(new InstantCommand(intakeSlider::Hold, intakeSlider));

        new Trigger(() -> gamepad2.right_stick_y > 0.5)
                .whenActive(new InstantCommand(virtual4Bar::Raise, virtual4Bar));

        new Trigger(() -> gamepad2.right_stick_y < -0.5)
                .whenActive(new InstantCommand(virtual4Bar::Lower, virtual4Bar));

        new Trigger(() -> gamepad2.right_stick_x > 0.5)
                .whenActive(new InstantCommand(turret::TurnRight, turret));

        new Trigger(() -> gamepad2.right_stick_x < -0.5)
                .whenActive(new InstantCommand(turret::TurnLeft, turret));

        new Trigger(() -> gamepad2.right_trigger > 0.5)
                .whenActive(new InstantCommand(intakeClaw::Grab, intakeClaw));

        new Trigger(() -> gamepad2.left_trigger > 0.5)
                .whenActive(new InstantCommand(intakeClaw::Release, intakeClaw));

        // Register all subsystems
        register(intakeSlider, virtual4Bar, intakeClaw, turret); //, limeLight);

        // update telemetry every loop
        schedule(SounderBotBaseRunCommand.createTelemetryEnabledOnlyInstance(telemetry));
    }

    @Override
    public void run() {
        super.run();
    }
}