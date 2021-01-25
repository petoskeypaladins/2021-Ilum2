/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveCommand extends Command {
  Timer timer = new Timer();
  double goal;
  double MovingSpeed;
  public AutoDriveCommand(double inches, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    goal = inches;
    MovingSpeed = speed;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.leftEncoder.setPosition(0);
    Robot.driveSubsystem.rightEncoder.setPosition(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveSubsystem.drive.arcadeDrive(goal>0?MovingSpeed:-MovingSpeed, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(timer.get() > 1) return true;
    // else return false;
    if(goal > 0) return (Robot.driveSubsystem.leftEncoder.getPosition() > goal);
    else return Robot.driveSubsystem.leftEncoder.getPosition() < goal;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSubsystem.drive.arcadeDrive(0, 0);
    Robot.driveSubsystem.leftEncoder.setPosition(0);
    Robot.driveSubsystem.rightEncoder.setPosition(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
