package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.constants.BucketConstants;

public class BucketSubsystem extends SubsystemBase {
    private final Servo leftArm;
    private final Servo rightArm;
    private final Servo leftBucket;
    private final Servo rightBucket;
    public BucketSubsystem(RobotHardware robot) {
        leftArm = robot.leftArm;
        rightArm = robot.rightArm;

        leftBucket = robot.leftBucket;
        rightBucket = robot.rightBucket;
    }
    public void upPosition() {
        leftArm.setPosition(BucketConstants.BUCKET_UP);
        rightArm.setPosition(BucketConstants.BUCKET_UP);

        leftBucket.setPosition(0.4);
        rightBucket.setPosition(0.4);
    }

    public void downPosition() {
        leftArm.setPosition(BucketConstants.BUCKET_DOWN);
        rightArm.setPosition(BucketConstants.BUCKET_DOWN);

        leftBucket.setPosition(BucketConstants.BUCKET_DUMP_RESET);
        rightBucket.setPosition(BucketConstants.BUCKET_DUMP_RESET);
    }

    public void dump() {
        leftBucket.setPosition(BucketConstants.BUCKET_DUMP);
        rightBucket.setPosition(BucketConstants.BUCKET_DUMP);
    }
}