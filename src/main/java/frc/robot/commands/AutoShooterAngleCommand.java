// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.concurrent.ConcurrentSkipListMap;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoShooterAngleCommand extends Command {
  int goal;
  public AutoShooterAngleCommand(int counts) {
    // Use requires() here to declare subsystem dependencies
    goal = -counts;
    requires(Robot.m_angleSubsystem);
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_angleSubsystem.shooterAngle.set(0.4);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_angleSubsystem.shooterAngle.getSelectedSensorPosition() < goal;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_angleSubsystem.shooterAngle.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
