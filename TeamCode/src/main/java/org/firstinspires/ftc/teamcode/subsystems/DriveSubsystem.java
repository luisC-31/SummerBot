package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

public class DriveSubsystem extends SubsystemBase {
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    private final GoBildaPinpointDriver pinpoint;

    public DriveSubsystem(RobotHardware robot) {
        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;
        pinpoint = robot.pinpoint;
    }

    public void drive(double y, double x, double turn) {

        double frontLeftPower = y + x + turn;
        double frontRightPower = y - x - turn;
        double backLeftPower = y - x + turn;
        double backRightPower = y + x - turn;

        double max = Math.max(
                Math.abs(frontLeftPower),
                Math.max(
                        Math.abs(frontRightPower),
                        Math.max(
                                Math.abs(backLeftPower),
                                Math.abs(backRightPower)))
        );

        if (max > 1) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void driveFieldCentric(double y, double x, double turn) {

        double heading = getHeading(AngleUnit.RADIANS);

        // Rotate the field-oriented joystick vector by -heading to get it
        // into the robot's frame. (Equivalent to the common
        // cos(-heading)/sin(-heading) formulation, simplified via trig
        // identities.)
        double rotatedX = x * Math.cos(heading) + y * Math.sin(heading);
        double rotatedY = -x * Math.sin(heading) + y * Math.cos(heading);

        drive(rotatedY, rotatedX, turn);
    }

    public double getHeading(AngleUnit unit) {
        // pinpoint.getHeading() returns the value natively in Radians
        double headingInRadians = pinpoint.getHeading(AngleUnit.RADIANS);
        return unit.fromRadians(headingInRadians);
    }

    public void resetIMU() {
        pinpoint.resetPosAndIMU();
    }

    @Override
    public void periodic() {
        pinpoint.update();
    }
}