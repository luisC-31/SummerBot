package org.firstinspires.ftc.teamcode.opmode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommand;
import org.firstinspires.ftc.teamcode.constants.*;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.*;
@TeleOp(name = "Main TeleOp, Right Test")
public class MainTeleOpRightServoTest extends CommandOpMode {
    private RobotHardware robot;
    private DriveSubsystem driveSubsystem;
    private LiftSubsystem liftSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private BucketSubsystem bucketSubsystem;
    private GamepadEx driverGamepad;

    @Override
    public void initialize() {
        robot = new RobotHardware();
        robot.init(hardwareMap);

        driverGamepad = new GamepadEx(gamepad1);

        driveSubsystem = new DriveSubsystem(robot);
        liftSubsystem = new LiftSubsystem(robot);
        intakeSubsystem = new IntakeSubsystem(robot);
        bucketSubsystem = new BucketSubsystem(robot);

        setCommands();
        setButtons();
    }

    private void setCommands() {
        driveSubsystem.setDefaultCommand(
                new DriveCommand(
                        driveSubsystem,
                        () -> gamepad1.left_stick_y,
                        () -> -gamepad1.left_stick_x,
                        () -> gamepad1.right_stick_x
                )
        );
    }

    private void setButtons() {
        new GamepadButton(driverGamepad,
                GamepadKeys.Button.BACK)
                .whenPressed(driveSubsystem::resetIMU);

        new GamepadButton(driverGamepad,
                GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakeCommand(intakeSubsystem));

        new GamepadButton(driverGamepad,
                GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new LiftCommand(liftSubsystem,LiftConstants.LEVEL_0));

        new GamepadButton(driverGamepad,
                GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new LiftCommand(liftSubsystem,LiftConstants.LEVEL_1));

        new GamepadButton(driverGamepad,GamepadKeys.Button.DPAD_UP)
                .whenPressed(new LiftCommand(liftSubsystem,LiftConstants.LEVEL_2));

        new GamepadButton(driverGamepad,GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(bucketSubsystem::downPositionRight,bucketSubsystem));

        new GamepadButton(driverGamepad,GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(bucketSubsystem::upPositionRight,bucketSubsystem));

        new GamepadButton(driverGamepad, GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(bucketSubsystem::dumpRight, bucketSubsystem));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        telemetry.addData("Heading",driveSubsystem.getHeading(AngleUnit.RADIANS));

        telemetry.addData("Left Slide",liftSubsystem.getLeftPosition());
        telemetry.addData("Right Slide",liftSubsystem.getRightPosition());

        telemetry.update();
    }
}