package frc.robot;
import frc.robot.Control;
import frc.robot.VisionControl;
import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public static Joystick Controller;

    public static void inputManager() {
        double yLeft = Controller.getRawAxis(1);
        double xRight = Controller.getRawAxis(4);
        boolean rBumper = Controller.getRawButton(6);

        Control.control(yLeft, xRight);
        VisionControl.visionControl(rBumper);
    }
}
