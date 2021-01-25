/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ShooterLaunchSubsystem extends Subsystem {
  TalonSRX launcher1 = new TalonSRX(RobotMap.SHOOTER_LAUNCH_WHEEL_1);
  TalonSRX launcher2 = new TalonSRX(RobotMap.SHOOTER_LAUNCH_WHEEL_2);
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void spinCommand(double speed) {
    launcher1.set(ControlMode.PercentOutput, -speed);
    launcher2.set(ControlMode.PercentOutput, -speed);
  }

  public int getLaunchSpeed() {
    return launcher2.getSensorCollection().getPulseWidthVelocity();
  }

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
