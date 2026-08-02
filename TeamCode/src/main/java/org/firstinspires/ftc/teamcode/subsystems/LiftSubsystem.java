package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.constants.LiftConstants;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

public class LiftSubsystem extends SubsystemBase {
    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private final PIDController controller;

    public LiftSubsystem(RobotHardware robot) {
        leftSlide = robot.leftSlide;
        rightSlide = robot.rightSlide;

        controller = new PIDController(
                LiftConstants.kP,
                LiftConstants.kI,
                LiftConstants.kD
        );
    }

    public void setPosition(int position) {
        LiftConstants.targetPosition = position;
    }


    @Override
    public void periodic() {
        controller.setPID(
                LiftConstants.kP,
                LiftConstants.kI,
                LiftConstants.kD
        );

        int currentPosition = (leftSlide.getCurrentPosition() + rightSlide.getCurrentPosition()) / 2;

        double pid = controller.calculate(currentPosition, LiftConstants.targetPosition);

        double power = pid + LiftConstants.kG;
        double maxPower;

        if (power > 0) {
            maxPower = 0.4;   // going up
        }
        else {
            maxPower = 0.3;  // going down
        }

        power = Math.max(
                -maxPower,
                Math.min(
                        maxPower,
                        power
                )
        );

        LiftConstants.currentPower = power;

        leftSlide.setPower(power);
        rightSlide.setPower(power);

    }

    public boolean atTarget() {
        int averagePosition = (leftSlide.getCurrentPosition() + rightSlide.getCurrentPosition()) / 2;

        return Math.abs(LiftConstants.targetPosition - averagePosition) < 20;
    }



    public int getLeftPosition() {
        return leftSlide.getCurrentPosition();
    }


    public int getRightPosition() {
        return rightSlide.getCurrentPosition();
    }

    public double getPower() {
        return LiftConstants.currentPower;
    }
}