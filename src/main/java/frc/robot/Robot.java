/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.commands.AutoDriveTest;
import frc.robot.commands.AutoGroup;
import frc.robot.commands.ShieldGeneratorGroup;
import frc.robot.commands.TestCommandGroup;
import frc.robot.commands.TrenchRunGroup;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterLaunchSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public static ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
  public static ShooterLaunchSubsystem shooterLaunchSubsystem = new ShooterLaunchSubsystem();
  public static ColorWheelSubsystem wheelSubsystem = new ColorWheelSubsystem();
  public static ClimbSubsystem climbSubsystem = new ClimbSubsystem();  
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Double> shooterSpeedChooser = new SendableChooser<>();
  public static double shooterSpeed =0.65;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");
    public static double x,y,area,target;
    boolean hackyWorkaround = true;
  

  /**
   * Th2is function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    // UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    // camera.setResolution(320, 240);
    m_chooser.setDefaultOption("Default Auto", new AutoGroup());
    m_chooser.addOption("test turn thing", new TestCommandGroup());
    m_chooser.addOption("Trench Run", new TrenchRunGroup());
    m_chooser.addOption("Shield Generator run", new ShieldGeneratorGroup());
    // chooser.addOption("My Auto", new MyAutoCommand());
  
    SmartDashboard.putData("Auto mode", m_chooser);
    
    shooterSpeedChooser.addOption("Full", 1.);
    shooterSpeedChooser.setDefaultOption("95 percent", 0.95);
    shooterSpeedChooser.addOption("90 percent", 0.90);
    shooterSpeedChooser.addOption("85 percent", 0.85);
    shooterSpeedChooser.addOption("80 percent", 0.80);
    shooterSpeedChooser.addOption("75 percent", 0.75);
    shooterSpeedChooser.addOption("70 percent", 0.70);
    shooterSpeedChooser.addOption("65 percent", 0.65);
    shooterSpeedChooser.addOption("60 percent", 0.60);
    shooterSpeedChooser.addOption("50 percent", 0.50);

    SmartDashboard.putData("Shooter Speed", shooterSpeedChooser);
    Robot.driveSubsystem.gyro.calibrate();
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
      }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Encoder Position", -Robot.driveSubsystem.leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Encoder Position", Robot.driveSubsystem.rightEncoder.getPosition());
    if(Robot.m_oi.xbox.getStartButtonPressed()) {
      Robot.driveSubsystem.leftEncoder.setPosition(0);
      Robot.driveSubsystem.rightEncoder.setPosition(0);
      Robot.driveSubsystem.gyro.calibrate();
    }
    SmartDashboard.putNumber("LeftRatio", Robot.driveSubsystem.leftEncoder.getPositionConversionFactor());
    SmartDashboard.putBoolean("Limit Switch Value", Robot.shooterFeederSubsystem.proxSensor.get());
    SmartDashboard.putNumber("shooter velocity", Robot.shooterLaunchSubsystem.getLaunchSpeed());
    // shooterSpeed = shooterSpeedChooser.getSelected();
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    target = tv.getDouble(0.0);
    SmartDashboard.putNumber("Target", target);
    SmartDashboard.putNumber("T X", x);
    SmartDashboard.putNumber("T Y", y);
    SmartDashboard.putNumber("T Area", area);
    SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
    if(Robot.m_oi.flightStick.getRawButton(8)) NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    if(Robot.m_oi.flightStick.getRawButton(7)) NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    SmartDashboard.putString("test", table.getEntry("camMode").getNumber(69.420).toString());
 
    if(Robot.m_oi.xbox.getStartButton()) hackyWorkaround = true;
    if(Robot.m_oi.xbox.getStickButton(Hand.kRight)) hackyWorkaround = false;
    if(hackyWorkaround) NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    else NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3); 
    SmartDashboard.putString("test", table.getEntry("ledMode").getNumber(69.420).toString());
    SmartDashboard.putNumber("Gyro angle",Robot.driveSubsystem.gyro.getAngle());
    SmartDashboard.putString("Color needed", DriverStation.getInstance().getGameSpecificMessage());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new DriveCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
