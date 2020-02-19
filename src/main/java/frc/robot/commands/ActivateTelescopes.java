/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Factory;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbSubsystem;

public class ActivateTelescopes extends CommandBase {
  /**
   * Creates a new ActivateTelescopes.
   */

  private final ClimbSubsystem cs;
  private final XboxController tele;

  private double Y;

  public ActivateTelescopes() {
    // Use addRequirements() here to declare subsystem dependencies.
    cs = Factory.getClimb();
    tele = RobotContainer.getTele();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (tele.getY() > 0.1 || tele.getY() < -0.1)  { 
      cs.setActivated(true);
    }
    else {
      cs.setActivated(false);
    }
    System.out.println("\n-------" + cs.getActivated());
    System.out.println(cs.getEnable());
    Y = tele.getY();

    cs.spinTeleMotor(Y);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cs.setActivated(false);
    cs.spinTeleMotor(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
