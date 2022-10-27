package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import frc.robot.Control;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionControl {
    public static void turnToTarget(double tx) {
        Control.controlRight(-1*(tx/100));
        Control.controlLeft(tx/100);
    }
    public static void visionControl(boolean rBumper) {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tx = table.getEntry("tx").getDouble(0.0);
        double ty = table.getEntry("ty").getDouble(0.0);
        double ta = table.getEntry("ta").getDouble(0.0);

        if (rBumper) {
            turnToTarget(tx);
        }
    }
}
