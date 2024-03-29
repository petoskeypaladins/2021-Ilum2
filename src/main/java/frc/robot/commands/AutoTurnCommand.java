// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoTurnCommand extends Command {
  double goal;
  double speed;
  public AutoTurnCommand(double degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
    goal = degrees;
    speed = 0.5;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveSubsystem.gyro.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(goal < 0) {
      if(Robot.m_driveSubsystem.gyro.getAngle() > (goal / 3)) speed = 0.45;
      else if ((Robot.m_driveSubsystem.gyro.getAngle() <= goal/3) && (Robot.m_driveSubsystem.gyro.getAngle() > goal * 3/4)) speed = (0.45 - (.235/(.75 * -goal)) * Robot.m_driveSubsystem.gyro.getAngle() );
      else speed = 0.215;
      Robot.m_driveSubsystem.driveTrain.arcadeDrive(0, -speed);
    } else {
      if(Robot.m_driveSubsystem.gyro.getAngle() < (goal / 3)) speed = 0.45;
      else if ((Robot.m_driveSubsystem.gyro.getAngle() >= goal/3) && (Robot.m_driveSubsystem.gyro.getAngle() < goal * 3/4)) speed = (0.45 - (.235/(.75 * goal)) * Robot.m_driveSubsystem.gyro.getAngle() );
      else speed = 0.215;
      Robot.m_driveSubsystem.driveTrain.arcadeDrive(0, speed);
    }

    SmartDashboard.putNumber("Gyro Angle", Robot.m_driveSubsystem.gyro.getAngle());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(goal < 0) {
      return (Robot.m_driveSubsystem.gyro.getAngle() < goal);
    } else {
      return (Robot.m_driveSubsystem.gyro.getAngle() > goal);
    }
      // return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
