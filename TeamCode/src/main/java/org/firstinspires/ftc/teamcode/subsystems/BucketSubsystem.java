package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BucketSubsystem extends SubsystemBase {
    private final Servo leftArm;
    private final Servo rightArm;
    private final Servo leftBucket;
    private final Servo rightBucket;

    public static final double BUCKET_UP = 0.4;
    public static final double BUCKET_DOWN = 0.5;
    public static final double BUCKET_DUMP = 0.05;
    public static final double BUCKET_DUMP_RESET = 0.45;
    public BucketSubsystem(HardwareMap hardwareMap) {
        leftArm = hardwareMap.get(Servo.class, "leftArm");
        rightArm = hardwareMap.get(Servo.class, "rightArm");
        leftBucket = hardwareMap.get(Servo.class, "leftBucket");
        rightBucket = hardwareMap.get(Servo.class, "rightBucket");

        rightArm.setDirection(Servo.Direction.REVERSE);
        rightBucket.setDirection(Servo.Direction.REVERSE);
    }
    public void upPosition() {
        leftArm.setPosition(BUCKET_UP);
        rightArm.setPosition(BUCKET_UP);

        leftBucket.setPosition(0.4);
        rightBucket.setPosition(0.4);
    }

    public void downPosition() {
        leftArm.setPosition(BUCKET_DOWN);
        rightArm.setPosition(BUCKET_DOWN);

        leftBucket.setPosition(BUCKET_DUMP_RESET);
        rightBucket.setPosition(BUCKET_DUMP_RESET);
    }

    public void dump() {
        leftBucket.setPosition(BUCKET_DUMP);
        rightBucket.setPosition(BUCKET_DUMP);
    }
}