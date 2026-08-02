package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.command.CommandOpMode;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.constants.LiftConstants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.commands.LiftCommand;
import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final GamepadEx driverGamepad;
    public final LiftSubsystem liftSubsystem;
    private final BucketSubsystem bucketSubsystem;

    public RobotContainer(CommandOpMode opMode) {
        driverGamepad = new GamepadEx(opMode.gamepad1);

        RobotHardware robot = new RobotHardware();

        robot.init(hardwareMap);

        driveSubsystem = new DriveSubsystem(robot);
        liftSubsystem = new LiftSubsystem(robot);
        bucketSubsystem = new BucketSubsystem(robot);
        intakeSubsystem = new IntakeSubsystem(robot);

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
                new IntakeCommand(intakeSubsystem));

        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftConstants.LEVEL_0));

        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftConstants.LEVEL_1));

        new GamepadButton(driverGamepad,GamepadKeys.Button.DPAD_UP).whenPressed(
                new LiftCommand(liftSubsystem,
                        LiftConstants.LEVEL_2));

        new GamepadButton(driverGamepad,GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new InstantCommand(bucketSubsystem::downPosition,bucketSubsystem));

        new GamepadButton(driverGamepad,GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new InstantCommand(bucketSubsystem::upPosition,bucketSubsystem));

        new GamepadButton(driverGamepad,GamepadKeys.Button.Y).whenPressed(
                new InstantCommand(bucketSubsystem::dump,bucketSubsystem));
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public LiftSubsystem getLiftSubsystem() {
        return liftSubsystem;
    }
}