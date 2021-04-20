// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleOpDriveCommand extends Command {
  boolean backForwards = false;

  public TeleOpDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveSubsystem.leftMotor1.follow(Robot.m_driveSubsystem.leftMotor2);
    Robot.m_driveSubsystem.rightMotor1.follow(Robot.m_driveSubsystem.rightMotor2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.xbox.getBumperPressed(Hand.kLeft)) {
      backForwards = !backForwards;
    }
    if(backForwards) {
      Robot.m_driveSubsystem.driveTrain.arcadeDrive(-Robot.m_oi.xbox.getY(Hand.kLeft), -Robot.m_oi.xbox.getX(Hand.kRight));
    } else {
      Robot.m_driveSubsystem.driveTrain.arcadeDrive(Robot.m_oi.xbox.getY(Hand.kLeft), Robot.m_oi.xbox.getX(Hand.kRight));
    }
    SmartDashboard.putNumber("Left Encoder Counts", Robot.m_driveSubsystem.leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Encoder Counts", Robot.m_driveSubsystem.rightEncoder.getPosition());
    SmartDashboard.putNumber("Gyro Angle", Robot.m_driveSubsystem.gyro.getAngle());
    SmartDashboard.putBoolean("Backwards = Forwards", backForwards);

    if(Robot.m_oi.xbox.getStartButtonPressed()) {
      Robot.m_driveSubsystem.leftEncoder.setPosition(0);
      Robot.m_driveSubsystem.rightEncoder.setPosition(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
