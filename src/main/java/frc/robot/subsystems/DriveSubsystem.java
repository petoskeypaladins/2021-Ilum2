/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;

/**
 * An example subsystem. You can replace with me with your own subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  CANSparkMax leftMotor1 = new CANSparkMax(RobotMap.LEFT_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
  CANSparkMax leftMotor2 = new CANSparkMax(RobotMap.LEFT_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);
  public CANEncoder leftEncoder = leftMotor2.getEncoder();
  CANSparkMax rightMotor1 = new CANSparkMax(RobotMap.RIGHT_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
  CANSparkMax rightMotor2 = new CANSparkMax(RobotMap.RIGHT_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);
  public CANEncoder rightEncoder = new CANEncoder(rightMotor2);
  public SpeedControllerGroup left = new SpeedControllerGroup(leftMotor1, leftMotor2);
  SpeedControllerGroup right = new SpeedControllerGroup(rightMotor1, rightMotor2);  
  public DifferentialDrive drive = new DifferentialDrive(left, right);
  public ADIS16448_IMU gyro = new ADIS16448_IMU();
  
  @Override
  protected void initDefaultCommand() {
    // Set the default command for a susystem here.
    // setDefaultCommand(new MySpecialCmmand());
    setDefaultCommand(new DriveCommand());
    leftEncoder.setPositionConversionFactor(1.8);
    rightEncoder.setPositionConversionFactor(1.8);
   }
}
