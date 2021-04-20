// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.Compressor;
 import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpIntakeCommand;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
/** Add your docs here. */
public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX wheels = new TalonSRX(RobotMap.INTAKE_WHEEL_MOTOR);
  public TalonSRX hopper = new TalonSRX(11);
  public Compressor compressor = new Compressor();
  public DoubleSolenoid intakeArm = new DoubleSolenoid(0,1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleOpIntakeCommand());
  }
}
