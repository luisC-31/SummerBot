package org.firstinspires.ftc.teamcode.constants;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class LiftConstants {
    public static double kPLeft = 0.002;
    public static double kILeft = 0.0;
    public static double kDLeft = 0.0002;

    public static double kPRight = 0.002;
    public static double kIRight = 0.0;
    public static double kDRight = 0.0002;
    public static double kG = 0.025;
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
