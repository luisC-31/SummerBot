package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.command.CommandOpMode;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.commands.LiftCommand;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final GamepadEx driverGamepad;
    public final LiftSubsystem liftSubsystem;

    public RobotContainer(CommandOpMode opMode) {
        driverGamepad = new GamepadEx(opMode.gamepad1);

        driveSubsystem = new DriveSubsystem(opMode.hardwareMap);

        intakeSubsystem = new IntakeSubsystem(opMode.hardwareMap);
        liftSubsystem = new LiftSubsystem(opMode.hardwareMap);

        driveSubsystem.setDefaultCommand(
                new DriveCommand(
                        driveSubsystem,
                        () -> opMode.gamepad1.left_stick_y,
                        () -> -opMode.gamepad1.left_stick_x,
                        () -> opMode.gamepad1.right_stick_x
                )
        );


        new GamepadButton(driverGamepad, GamepadKeys.Button.BACK).whenPressed(driveSubsystem::resetHeading);

        new GamepadButton(driverGamepad, GamepadKeys.Button.A).toggleWhenPressed(
                new IntakeCommand(intakeSubsystem, 1.0)
        );

        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftSubsystem.LEVEL_0)
        );

        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftSubsystem.LEVEL_1)
        );

        new GamepadButton(driverGamepad,GamepadKeys.Button.DPAD_UP).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftSubsystem.LEVEL_2)
        );
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public LiftSubsystem getLiftSubsystem() {
        return liftSubsystem;
    }
}