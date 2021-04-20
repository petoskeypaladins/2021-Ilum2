// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoResetEncoderCommand extends Command {
  public AutoResetEncoderCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveSubsystem.leftEncoder.setPosition(0);
    Robot.m_driveSubsystem.rightEncoder.setPosition(0);
    Robot.m_driveSubsystem.gyro.reset();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return ((Robot.m_driveSubsystem.leftEncoder.getPosition() == 0)&&(Robot.m_driveSubsystem.rightEncoder.getPosition() == 0));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
