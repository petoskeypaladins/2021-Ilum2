/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbCommand;

/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public WPI_TalonSRX WinchMotor1 = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_1);
public WPI_TalonSRX WinchMotor2 = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_2);
public SpeedControllerGroup Winch = new SpeedControllerGroup(WinchMotor1, WinchMotor2);
public DoubleSolenoid ClimbRotate = new DoubleSolenoid(5, 4); //add new numbers
public DoubleSolenoid ClimbUp = new DoubleSolenoid(6, 7); //add new numbers

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimbCommand());
  }

  public void RotateSolenoidUpwards() {
    ClimbRotate.set(Value.kForward);
  }

  public void RotateSolenoidDownwards() {
    ClimbRotate.set(Value.kReverse);
  }

  public void ExtendSolenoid() {
    ClimbUp.set(Value.kForward);
  }

  public void RetractSolenoid() {
    ClimbUp.set(Value.kReverse);
  }

  public void WindWinch(double speed) {
    Winch.set(speed);
  }

}
