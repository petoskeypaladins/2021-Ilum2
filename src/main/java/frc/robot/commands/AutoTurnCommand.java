/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoTurnCommand extends Command {
  double goal;
  boolean right;
  public AutoTurnCommand(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    goal = angle;
    right = goal > 0;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.gyro.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(right) Robot.driveSubsystem.drive.arcadeDrive(0, 0.3);
    else Robot.driveSubsystem.drive.arcadeDrive(0, -0.3);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(right) return Robot.driveSubsystem.gyro.getAngle() > goal;
    else return Robot.driveSubsystem.gyro.getAngle() < goal;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSubsystem.drive.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
