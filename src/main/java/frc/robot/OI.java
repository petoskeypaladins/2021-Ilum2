// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoShooterAngleCommand;
import frc.robot.commands.TeleOpShooterLaunchCommand;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands
 * and command groups that allow control of the robot.
 */
public class OI {
  public XboxController xbox = new XboxController(0);
  public Joystick flightStick = new Joystick(1);  
  public JoystickButton shooterLaunchButton = new JoystickButton(flightStick, 1);
  public JoystickButton greenAngleButton = new JoystickButton(xbox, 1);
  public JoystickButton yellowAngleButton = new JoystickButton(xbox, 4);
  public JoystickButton blueAngleButton = new JoystickButton(xbox, 3);
  public JoystickButton redAngleButton = new JoystickButton(xbox , 2);

  public OI() {
    shooterLaunchButton.whenPressed(new TeleOpShooterLaunchCommand());
    greenAngleButton.whenPressed(new AutoShooterAngleCommand(15155));
    yellowAngleButton.whenPressed(new AutoShooterAngleCommand(12000));
    blueAngleButton.whenPressed(new AutoShooterAngleCommand(10000));
    redAngleButton.whenPressed(new AutoShooterAngleCommand(9800));
  }
}
