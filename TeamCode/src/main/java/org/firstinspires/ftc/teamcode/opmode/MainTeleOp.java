package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer;


@TeleOp(name = "Main TeleOp", group = "Competition")
public class MainTeleOp extends CommandOpMode {

    private RobotContainer robot;


    @Override
    public void initialize() {

        robot = new RobotContainer(this);

    }


    @Override
    public void run() {

        CommandScheduler.getInstance().run();

        telemetry.addData(
                "Heading",
                robot.getDriveSubsystem().getHeading()
        );

        telemetry.update();
    }


}