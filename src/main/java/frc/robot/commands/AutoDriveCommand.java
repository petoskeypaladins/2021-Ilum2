// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoDriveCommand extends Command {

  double goal;
  double progress;
  public AutoDriveCommand(double inches) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
    goal = inches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveSubsystem.leftEncoder.setPosition(0);
    Robot.m_driveSubsystem.rightEncoder.setPosition(0);
    Robot.m_driveSubsystem.leftMotor1.follow(Robot.m_driveSubsystem.leftMotor2);
    Robot.m_driveSubsystem.rightMotor1.follow(Robot.m_driveSubsystem.rightMotor2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (goal > 0) Robot.m_driveSubsystem.driveTrain.arcadeDrive(0.5, 0);
      else Robot.m_driveSubsystem.driveTrain.arcadeDrive(-0.5, 0);
      SmartDashboard.putNumber("Left Encoder Counts", Robot.m_driveSubsystem.leftEncoder.getPosition());
      SmartDashboard.putNumber("Right Encoder Counts", Robot.m_driveSubsystem.rightEncoder.getPosition());
      SmartDashboard.putNumber("goal", goal);
      progress = Robot.m_driveSubsystem.leftEncoder.getPosition()*40/23;
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return ((Robot.m_driveSubsystem.leftEncoder.getPosition()) < (goal * (23/40)));
    if (goal > 0) return (progress > goal);
    else return (progress < goal);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveSubsystem.driveTrain.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
