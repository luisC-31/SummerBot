package org.firstinspires.ftc.teamcode.opmode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommand;
import org.firstinspires.ftc.teamcode.constants.*;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.*;
@TeleOp(name = "ServoMoveTest")
public class ServoMoveTest extends CommandOpMode {
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

        Servo leftArm;
        Servo leftBucket;
        Servo rightArm;
        Servo rightBucket;

        leftArm = robot.leftArm;
        leftBucket = robot.leftBucket;
        rightArm = robot.rightArm;
        rightBucket = robot.rightBucket;

        leftArm.setPosition(0.);
        leftBucket.setPosition(0.8);
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        telemetry.addData("Heading",driveSubsystem.getHeading());

        telemetry.addData("Left Slide",liftSubsystem.getLeftPosition());
        telemetry.addData("Right Slide",liftSubsystem.getRightPosition());

        telemetry.update();
    }
}