package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.constants.LiftConstants;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

import static org.firstinspires.ftc.teamcode.constants.LiftConstants.*;

public class LiftSubsystem extends SubsystemBase {
    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private final PIDController controllerLeft, controllerRight, controllerLeftD, controllerRightD;
    int currentPosition;
    public LiftSubsystem(RobotHardware robot) {
        leftSlide = robot.leftSlide;
        rightSlide = robot.rightSlide;

        controllerLeft = new PIDController(
                LiftConstants.kPLeft,
                LiftConstants.kILeft,
                LiftConstants.kDLeft
        );
        controllerRight = new PIDController(
                LiftConstants.kPRight,
                LiftConstants.kIRight,
                LiftConstants.kDRight
        );
        controllerLeftD = new PIDController(
                LiftConstants.kPLeftD,
                LiftConstants.kILeftD,
                LiftConstants.kDLeftD
        );
        controllerRightD = new PIDController(
                LiftConstants.kPRightD,
                LiftConstants.kIRightD,
                LiftConstants.kDRightD
        );
    }

    public void setPosition(int position) {
        LiftConstants.targetPosition = position;
    }


    @Override
    public void periodic() {
        controllerLeft.setPID(
                LiftConstants.kPLeft,
                LiftConstants.kILeft,
                LiftConstants.kDLeft
        );
        controllerRight.setPID(
                LiftConstants.kPRight,
                LiftConstants.kIRight,
                LiftConstants.kDRight
        );
        controllerLeftD.setPID(
                LiftConstants.kPLeftD,
                LiftConstants.kILeftD,
                LiftConstants.kDLeftD
        );
        controllerRightD.setPID(
                LiftConstants.kPRightD,
                LiftConstants.kIRightD,
                LiftConstants.kDRightD
        );

        currentPosition = (leftSlide.getCurrentPosition() + rightSlide.getCurrentPosition()) / 2;

        double pidL = controllerLeft.calculate(leftSlide.getCurrentPosition(), LiftConstants.targetPosition);
        double pidR = controllerRight.calculate(rightSlide.getCurrentPosition(), LiftConstants.targetPosition + rightOffset);
        double pidLD = controllerLeftD.calculate(leftSlide.getCurrentPosition(), LiftConstants.targetPosition);
        double pidRD = controllerRightD.calculate(rightSlide.getCurrentPosition(), LiftConstants.targetPosition + rightOffset);


        double powerL = pidL + LiftConstants.kG;
        double powerR = pidR + LiftConstants.kG;
        double maxPower;

        if (powerL > 0) {
            maxPower = maxPowerUp;   // going up
        }
        else {        // CHANGE OTHER ONE TOO
            powerL = pidLD + LiftConstants.kG;
            maxPower = 1;// going down
        }

        powerL = Math.max(
                -maxPower,
                Math.min(
                        maxPower,
                        powerL
                )
        );

        LiftConstants.currentPowerL = powerL;

        if (powerR > 0) {
            maxPower = maxPowerUp;   // going up
        }
        else {        // CHANGE OTHER ONE TOO
            powerR = pidRD + LiftConstants.kG;
            maxPower = 1;// going down
        }

        powerR = Math.max(
                -maxPower,
                Math.min(
                        maxPower,
                        powerR
                )
        );

        LiftConstants.currentPowerR = powerR;


        leftSlide.setPower(powerL);
        rightSlide.setPower(powerR);

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

    public double getPowerL() {
        return LiftConstants.currentPowerL;
    }
    public double getPowerR() {
        return LiftConstants.currentPowerR;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
}