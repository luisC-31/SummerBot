package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;
public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier ySupplier;
    private final DoubleSupplier xSupplier;
    private final DoubleSupplier turnSupplier;

    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier ySupplier, DoubleSupplier xSupplier, DoubleSupplier turnSupplier)
    {
        this.driveSubsystem = driveSubsystem;
        this.ySupplier = ySupplier;
        this.xSupplier = xSupplier;
        this.turnSupplier = turnSupplier;

        addRequirements(driveSubsystem);
    }


    @Override
    public void execute() {
        double y = -ySupplier.getAsDouble();
        double x = xSupplier.getAsDouble();
        double turn = turnSupplier.getAsDouble();

        double deadzone = 0.05;

        if(Math.abs(y) < deadzone)
            y = 0;

        if(Math.abs(x) < deadzone)
            x = 0;

        if(Math.abs(turn) < deadzone)
            turn = 0;

        driveSubsystem.driveFieldCentric(y, x, turn);
    }


    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0, 0, 0);
    }


    @Override
    public boolean isFinished() {
        return false;
    }
}