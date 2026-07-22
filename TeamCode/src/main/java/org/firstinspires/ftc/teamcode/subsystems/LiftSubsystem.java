package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class LiftSubsystem extends SubsystemBase {


    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private double currentPower = 0;


    private final PIDController controller;


    // Tune these later
    public static double kP = 0.0022;
    public static double kI = 0.0;
    public static double kD = 0.0002;


    // Gravity compensation
    public static double kG = 0.025;
    private int targetPosition = 0;

    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 600;
    public static final int LEVEL_2 = 1400;



    public LiftSubsystem(HardwareMap hardwareMap) {


        leftSlide = hardwareMap.get(
                DcMotor.class,
                "leftSlide"
        );


        rightSlide = hardwareMap.get(
                DcMotor.class,
                "rightSlide"
        );


        leftSlide.setDirection(
                DcMotor.Direction.FORWARD
        );

        rightSlide.setDirection(
                DcMotor.Direction.FORWARD
        );


        leftSlide.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );

        rightSlide.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );


        leftSlide.setMode(
                DcMotor.RunMode.STOP_AND_RESET_ENCODER
        );

        rightSlide.setMode(
                DcMotor.RunMode.STOP_AND_RESET_ENCODER
        );


        leftSlide.setMode(
                DcMotor.RunMode.RUN_USING_ENCODER
        );

        rightSlide.setMode(
                DcMotor.RunMode.RUN_USING_ENCODER
        );


        controller = new PIDController(
                kP,
                kI,
                kD
        );
    }



    public void setPosition(int position) {

        targetPosition = position;

    }



    @Override
    public void periodic() {
        controller.setPID(
                kP,
                kI,
                kD
        );

        int currentPosition =
                (leftSlide.getCurrentPosition()
                        + rightSlide.getCurrentPosition()) / 2;

        double pid = controller.calculate(
                currentPosition,
                targetPosition
        );


        double power = pid + kG;
        double maxPower;

        if (power > 0) {
            maxPower = 0.4;   // going up
        } else {
            maxPower = 0.3;  // going down
        }

        power = Math.max(
                -maxPower,
                Math.min(
                        maxPower,
                        power
                )
        );

        currentPower = power;

        leftSlide.setPower(power);
        rightSlide.setPower(power);

    }



    public boolean atTarget() {

        int averagePosition =
                (leftSlide.getCurrentPosition()
                        + rightSlide.getCurrentPosition()) / 2;

        return Math.abs(
                targetPosition - averagePosition
        ) < 20;
    }



    public int getLeftPosition() {

        return leftSlide.getCurrentPosition();

    }


    public int getRightPosition() {

        return rightSlide.getCurrentPosition();

    }

    public double getPower() {
        return currentPower;
    }
}