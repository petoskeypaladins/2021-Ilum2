// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpDriveCommand;

/** Add your docs here. */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public CANSparkMax leftMotor1 = new CANSparkMax(RobotMap.LEFT_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
  public CANSparkMax leftMotor2 = new CANSparkMax(RobotMap.LEFT_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);
  public CANSparkMax rightMotor1 = new CANSparkMax(RobotMap.RIGHT_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
  public CANSparkMax rightMotor2 = new CANSparkMax(RobotMap.RIGHT_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);
  public DifferentialDrive driveTrain = new DifferentialDrive(leftMotor2, rightMotor2);
  public CANEncoder leftEncoder = new CANEncoder(leftMotor2);
  public CANEncoder rightEncoder = new CANEncoder(rightMotor2);
  public ADIS16448_IMU gyro = new ADIS16448_IMU();


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    leftEncoder.setPositionConversionFactor(40/23);
    rightEncoder.setPositionConversionFactor(40/23);
    setDefaultCommand(new TeleOpDriveCommand());
  }
}
