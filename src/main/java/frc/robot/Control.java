package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;

public class Control {

    public static VictorSPX DriveTrainLeft0;
    public static VictorSPX DriveTrainLeft1;
    public static VictorSPX DriveTrainRight0;
    public static VictorSPX DriveTrainRight1;
  
    public static Joystick Controller;

    public static void controlLeft(double percent) {
        DriveTrainLeft0.set(ControlMode.PercentOutput, percent);
        DriveTrainLeft1.set(ControlMode.PercentOutput, percent);
    }

    public static void controlRight(double percent) {
        DriveTrainRight0.set(ControlMode.PercentOutput, percent);
        DriveTrainRight1.set(ControlMode.PercentOutput, percent);
    }

    public static void turn(double xRight, double yLeft) {
        //turn right
        if (xRight > 0) {
            //forward right
            if (yLeft >= 0.05) {
                if (xRight > 0) {xRight *= -1;}
                controlRight(xRight * (0.5 - (yLeft / 2)));
                controlLeft(xRight * (0.5 + (yLeft / 2)));
            }
            //backward right
            else if (yLeft <= -0.05) {
                if (xRight > 0) {xRight *= -1;}
                controlRight(xRight * (0.5 + (yLeft / 2)) * -1);
                controlLeft(xRight * (0.5 - (yLeft / 2)) * -1);
            }
            //spin right
            else {
                controlRight(xRight * -1);
                controlLeft(xRight);
            }
        }
        //turn left
        else if (xRight < 0) {
            //forward left
            if (yLeft >= 0.1) {
                if (xRight > 0) {xRight *= -1;}
                controlRight(xRight * (0.5 + (yLeft / 2)));
                controlLeft(xRight * (0.5 - (yLeft / 2)));
            }
            //backward left
            if (yLeft <= -0.1) {
                if (xRight > 0) {xRight *= -1;}
                controlRight(xRight * (0.5 - (yLeft / 2)) * -1);
                controlLeft(xRight * (0.5 + (yLeft / 2)) * -1);
            }
            //spin left
            else {
                controlRight(xRight);
                controlLeft(xRight * -1);
            }
        }
    }

    public static void move(double yLeft) {
        controlLeft(yLeft);
        controlRight(yLeft);
    }

    public static void control() {
        double yLeft = Controller.getRawAxis(1) * -1;
        double xRight = Controller.getRawAxis(4) * -1;

        if (xRight <= -0.1 || xRight >= 0.1) {
            turn(xRight, yLeft);
        } 
        else {
            move(yLeft);
        }

    }
}
