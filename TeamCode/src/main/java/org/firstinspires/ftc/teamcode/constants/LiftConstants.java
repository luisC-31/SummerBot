package org.firstinspires.ftc.teamcode.constants;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class LiftConstants {
    public static final double kPLeft = 0.002;
    public static final double kILeft = 0.0;
    public static final double kDLeft = 0.0002;

    public static final double kPRight = 0.002;
    public static final double kIRight = 0.0;
    public static final double kDRight = 0.0002;
    // _------------------------------------------------------------
    public static double kPLeftD = 0.002;
    public static double kILeftD = 0.0;
    public static double kDLeftD = 0.0002;

    public static double kPRightD = 0.002;
    public static double kIRightD = 0.0;
    public static double kDRightD = 0.0002;
    public static final double kG = 0.025;
    //-----------------------------------------------------------------
    public static int targetPosition = 0;
    public static int LEVEL_0 = 0;
    public static int LEVEL_1 = 600;
    public static int LEVEL_2 = 1400;
    public static int rightOffset = 24;
    public static double maxPowerUp = 0.8;
    public static double maxPowerDown = 0.7;
    public static double currentPowerL = 0;
    public static double currentPowerR = 0;


}
