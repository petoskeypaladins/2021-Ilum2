/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FillFeederCommand extends Command {
  public boolean IsItAuton;
  public FillFeederCommand(boolean IsAuton) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.shooterFeederSubsystem);
    IsItAuton = IsAuton;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Robot.shooterFeederSubsystem.proxSensor.get()) 
        Robot.shooterFeederSubsystem.feederSpin(0);
      else 
        Robot.shooterFeederSubsystem.feederSpin(0.15);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (IsItAuton) {
      return Robot.shooterFeederSubsystem.proxSensor.get();
    } else {return false;}
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
