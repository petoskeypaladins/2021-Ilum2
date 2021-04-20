// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
// import frc.robot.subsystems.ShooterLauncherSubsystem;

public class TeleOpShooterLaunchCommand extends Command {
  public TeleOpShooterLaunchCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_shooterLaunchSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  
    Robot.m_shooterLaunchSubsystem.shooter1.setInverted(true);  

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_shooterLaunchSubsystem.shooter1.set(0.8);
    Robot.m_shooterLaunchSubsystem.shooter2.set(-0.8);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (!Robot.m_oi.flightStick.getRawButton(1));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_shooterLaunchSubsystem.shooter1.set(0);
    Robot.m_shooterLaunchSubsystem.shooter2.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
