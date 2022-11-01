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
        //check for forward or backward
        double yLeftModifier = 0;
        if (yLeft > 0) {yLeftModifier = (1 - yLeft) * -1;}
        else if (yLeft < 0) {yLeftModifier = (1 + yLeft);}
        System.out.print(yLeftModifier);

        //check for curved turn
        boolean curve = false;
        if (yLeft >= 0.05 || yLeft <= 0.05) {curve = true;}

        //turn right
        if (xRight > 0) {
            //right curve
            if (curve) {
                controlRight(xRight * yLeftModifier);
                controlLeft(xRight);
            }
            //spin right
            else {
                controlRight(xRight * -1);
                controlLeft(xRight);
            }
        }
        //turn left
        else if (xRight < 0) {
            //left curve
            if (curve) {
                controlRight(-1 * xRight);
                controlLeft(-1 * xRight * yLeftModifier);
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

    public static void control(double yLeft, double xRight) {
        if (xRight <= -0.1 || xRight >= 0.1) {
            turn(xRight, yLeft);
        } 
        else if (yLeft <= -0.05 || yLeft >= -0.05){
            move(yLeft);
        }
    }
}
