package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotor intakeMotor;
    public IntakeSubsystem(RobotHardware robot) {
        intakeMotor = robot.intakeMotor;
    }

    public void runIntake() {
        intakeMotor.setPower(1);
    }


    public void stop() {
        intakeMotor.setPower(0);
    }
}