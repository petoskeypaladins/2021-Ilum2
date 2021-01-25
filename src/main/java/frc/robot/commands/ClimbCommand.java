/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbCommand extends Command {
  public ClimbCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climbSubsystem);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climbSubsystem.Winch.setInverted(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_oi.xbox.getYButtonPressed()) Robot.climbSubsystem.RotateSolenoidUpwards();
    if (Robot.m_oi.xbox.getAButtonPressed() && Robot.climbSubsystem.ClimbUp.get() == Value.kReverse) Robot.climbSubsystem.RotateSolenoidDownwards();
    if (Robot.m_oi.xbox.getPOV() == 0) Robot.climbSubsystem.ExtendSolenoid();
    if (Robot.m_oi.xbox.getPOV() == 180) Robot.climbSubsystem.RetractSolenoid();
    if (Robot.m_oi.xbox.getBackButton() && Robot.m_oi.xbox.getStartButton() && Robot.m_oi.flightStick.getPOV() == 180)
      Robot.climbSubsystem.WindWinch(-0.2);
    else if (Robot.m_oi.flightStick.getRawButtonPressed(RobotMap.EXTEND_WINCH) && Robot.climbSubsystem.ClimbUp.get() == Value.kReverse) {
      Robot.climbSubsystem.WindWinch(0.5);
      Robot.climbSubsystem.RotateSolenoidDownwards();
    }
    else Robot.climbSubsystem.WindWinch(0);
    SmartDashboard.getNumber("test again", Robot.m_oi.xbox.getPOV());
    
 
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
