// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoCircleCommand extends Command {
  double ratioNew;
  double goal;
  boolean right;
  double topSpeed = 0.5;
  public AutoCircleCommand(double ratio, double angle, boolean direction) {
    // Use requires() here to declaresubsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
    ratioNew = ratio;
    goal = angle;
    right = direction;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      Robot.m_driveSubsystem.gyro.reset();
    }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(right) Robot.m_driveSubsystem.driveTrain.tankDrive(topSpeed, topSpeed * ratioNew);
    else Robot.m_driveSubsystem.driveTrain.tankDrive(topSpeed * ratioNew, topSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(right) return (-Robot.m_driveSubsystem.rightEncoder.getPosition() > (radiusNew*goal));
    // else return (Robot.m_driveSubsystem.leftEncoder.getPosition() > (radiusNew*goal));
    if(right) return (Robot.m_driveSubsystem.gyro.getAngle() > goal);
    else return (Robot.m_driveSubsystem.gyro.getAngle() < -goal);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveSubsystem.driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
