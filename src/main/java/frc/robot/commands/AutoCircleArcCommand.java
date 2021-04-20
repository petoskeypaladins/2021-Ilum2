// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoCircleArcCommand extends Command {
  double leftDistance;
  double rightDistance;
  double speedChange;
  public AutoCircleArcCommand(double leftMotorDistance, double rightMotorDistance, double speedModifier) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
    leftDistance = leftMotorDistance;
    rightDistance = rightMotorDistance;
    speedChange = speedModifier;
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
    SmartDashboard.putNumber("Left Encoder Counts", Robot.m_driveSubsystem.leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Encoder Counts", Robot.m_driveSubsystem.rightEncoder.getPosition());
    if(leftDistance > rightDistance) {
      Robot.m_driveSubsystem.driveTrain.tankDrive((speedChange), ((rightDistance/leftDistance)*speedChange));
    } else {
      Robot.m_driveSubsystem.driveTrain.tankDrive(((leftDistance/rightDistance)*speedChange), (speedChange));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return ((Robot.m_driveSubsystem.leftEncoder.getPosition() >= leftDistance) && (Robot.m_driveSubsystem.rightEncoder.getPosition() <= -rightDistance));
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
