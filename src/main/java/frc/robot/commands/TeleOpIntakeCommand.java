// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleOpIntakeCommand extends Command {
  public TeleOpIntakeCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_intakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intakeSubsystem.intakeArm.set(Value.kReverse);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     if(Robot.m_oi.xbox.getBumperPressed(Hand.kRight)) Robot.m_intakeSubsystem.intakeArm.toggle();
    if(Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft) > 0.1 && Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) > 0.1) Robot.m_intakeSubsystem.wheels.set(ControlMode.PercentOutput, 0);
    else if(Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft) > 0.1) Robot.m_intakeSubsystem.wheels.set(ControlMode.PercentOutput, Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft) * -0.7);
    else if(Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) > 0.1) Robot.m_intakeSubsystem.wheels.set(ControlMode.PercentOutput, Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) *0.7);
    else Robot.m_intakeSubsystem.wheels.set(ControlMode.PercentOutput, 0);
   // SmartDashboard.putBoolean("Solenoid Value", Robot.m_intakeSubsystem.intakeArm.get());

    if(Robot.m_oi.flightStick.getRawAxis(1) > 0.1 || Robot.m_oi.flightStick.getRawAxis(1) < -0.1) Robot.m_intakeSubsystem.hopper.set(ControlMode.PercentOutput, -Robot.m_oi.flightStick.getRawAxis(1));
    else Robot.m_intakeSubsystem.hopper.set(ControlMode.PercentOutput, 0);
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
