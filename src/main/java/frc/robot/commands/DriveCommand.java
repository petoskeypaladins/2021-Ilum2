/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveCommand extends Command {
  boolean highGear = true;
  double speed;
  public DriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSubsystem);
   // Robot.driveSubsystem.left.setInverted(true);
 // Robot.driveSubsystem.leftEncoder.setInverted(true);
  Robot.driveSubsystem.leftEncoder.setPosition(0);
  Robot.driveSubsystem.rightEncoder.setPosition(0);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    highGear = !Robot.m_oi.flightStick.getRawButton(RobotMap.HIGH_GEAR);
    if(highGear) speed = 0.8;
    else speed = 0.4;
    Robot.driveSubsystem.drive.arcadeDrive(-Robot.m_oi.xbox.getY(Hand.kLeft)*speed, Robot.m_oi.xbox.getX(Hand.kRight)*speed);
    SmartDashboard.putBoolean("High Gear", highGear);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
