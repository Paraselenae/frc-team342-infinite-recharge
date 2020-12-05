/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightSubsystem extends SubsystemBase {
  private NetworkTable table; // Network table for limelight values
  private NetworkTableEntry tv, ty, tx, camMode, ledMode;
  private double yOffsetAngle, xOffsetAngle; // Field of view of the limelight
  private int cameraMode, lightMode, validTarget;
  private double limeError = 0.1; // Acceptable error from the limelight
  private double limelightAngleOffset = 15.0 /*12.95*/; // Angle of the limelight from flat ground
  private double targetHeight = 90.5;
  private double robotHeight = 21.0;
  private double degreesToRadians = Math.PI / 180;

  /**
   * Creates a new LimelightSubsystem.
   */
  public LimelightSubsystem() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tv = table.getEntry("tv");
    ty = table.getEntry("ty");
    tx = table.getEntry("tx");
    camMode = table.getEntry("camMode");
    ledMode = table.getEntry("ledMode");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    validTarget = tv.getNumber(0).intValue();
    yOffsetAngle = ty.getDouble(0.0);
    xOffsetAngle = tx.getDouble(0.0);
    cameraMode = camMode.getNumber(0).intValue();
    lightMode = ledMode.getNumber(0).intValue();

    SmartDashboard.putNumber("Distance from Target", getDistance());
  }

  /*
   * Gets distance offset from the robot to the target
   * 
   */
  public double getDistance() {
    return (targetHeight - robotHeight) / Math.tan((yOffsetAngle + limelightAngleOffset) * degreesToRadians);
    //return yOffsetAngle;
  }

  public double getLimelightOffsetAngle(double distance){
    return Math.atan((targetHeight - robotHeight) / distance) - yOffsetAngle;
  }

  /*
   * Returns the offset from the robot to the target
   * 
   */
  public double getXOffset() {
    return getDistance() * (Math.tan(xOffsetAngle * degreesToRadians));
  }

  /*
   * Returns the distance directly from the robot to the target
   * 
   */
  public double getDirectDistance() {
    return Math.sqrt(Math.pow(getXOffset(), 2) + Math.pow(getDistance(), 2));
  }

  /*
   * Changes camera mode and LEDs
   * 
   */
  public void switchCamMode() {
    camMode.setNumber((cameraMode == 0) ? 1 : 0);
    ledMode.setNumber((cameraMode == 0) ? 1 : 0);
  }

  public void visionOn() {
    camMode.setNumber(0);
    ledMode.setNumber(0);
  }

  public void visionOff() {
    camMode.setNumber(1);
    ledMode.setNumber(1);
  }

  /*
   * Returns horizontal angle of target offset from crosshair
   * 
   */
  public double getXOffsetAngle() {
      return xOffsetAngle + limeError;
  }

  /*
   * Returns vertical angle of target offset from crosshair
   * 
   */
  public double getYOffsetAngle() {
    return yOffsetAngle;
  }

  public boolean getValidTarget() {
    return (validTarget == 1) ? true : false;
  }

}

