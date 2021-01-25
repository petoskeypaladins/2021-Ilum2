/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LauncherSpeedCommand extends Command {
  boolean released;
  public LauncherSpeedCommand() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.shooterLaunchSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.shooterLaunchSubsystem.spinCommand(Robot.shooterSpeed);
    released = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Time left", timeSinceInitialized() - 15);
    if(Robot.m_oi.flightStick.getRawButtonReleased(RobotMap.LAUNCHER_START)) released = true;
    SmartDashboard.putBoolean("Debug 1", Robot.m_oi.flightStick.getRawButton(RobotMap.LAUNCHER_START));
    SmartDashboard.putBoolean("Debug 2", released);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return timeSinceInitialized() > 15// || (Robot.m_oi.flightStick.getRawButtonPressed(RobotMap.LAUNCHER_START) && released)
    ;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooterLaunchSubsystem.spinCommand(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
