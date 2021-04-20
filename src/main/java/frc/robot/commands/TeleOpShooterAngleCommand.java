// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleOpShooterAngleCommand extends Command {
  public TeleOpShooterAngleCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_angleSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.flightStick.getRawButton(4)) Robot.m_angleSubsystem.shooterAngle.set(0.4);
    else if (Robot.m_oi.flightStick.getRawButton(3)) Robot.m_angleSubsystem.shooterAngle.set(-0.3);
    else Robot.m_angleSubsystem.shooterAngle.set(0);
    SmartDashboard.putNumber("Shooter Angle Encoder", Robot.m_angleSubsystem.shooterAngle.getSelectedSensorPosition());
    if (Robot.m_angleSubsystem.ShooterSwitch.get() == true){
    Robot.m_angleSubsystem.shooterAngle.setSelectedSensorPosition(0);
    if(Robot.m_oi.flightStick.getRawButton(3)) {
      Robot.m_angleSubsystem.shooterAngle.set(0);
    }
    }
    //if (Robot.m_oi.xbox.getBackButtonPressed()) Robot.m_angleSubsystem.shooterAngle.setSelectedSensorPosition(0);
  }
    //if robot on,work;
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
