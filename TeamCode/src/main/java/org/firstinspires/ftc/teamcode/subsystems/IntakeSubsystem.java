package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class IntakeSubsystem extends SubsystemBase {

    private final DcMotor intakeMotor;


    public IntakeSubsystem(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(
                DcMotor.class,
                "intake"
        );

        intakeMotor.setDirection(
                DcMotor.Direction.REVERSE
        );

        intakeMotor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.FLOAT
        );
    }


    public void runIntake(double power) {

        intakeMotor.setPower(power);

    }


    public void stop() {

        intakeMotor.setPower(0);

    }
}