package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftCommand extends CommandBase {
    private final LiftSubsystem liftSubsystem;
    private final int position;

    public LiftCommand (LiftSubsystem liftSubsystem, int position) {
        this.liftSubsystem = liftSubsystem;
        this.position = position;

        addRequirements(liftSubsystem);
    }


    @Override
    public void initialize() {
        liftSubsystem.setPosition(position);
    }


    @Override
    public boolean isFinished() {
        return liftSubsystem.atTarget();
    }
}