// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CircleTest extends CommandGroup {
  /** Add your docs here. */
  public CircleTest() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.


    addSequential(new AutoCircleArcCommand(30, 43, 0.45));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(55, 34, 0.45));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(80));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(32, 17, 0.45));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(37, 80, 0.6));
    addSequential(new AutoWaitCommand(), 0.3);
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(35, 23, 0.5));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(95));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(43, 30, 0.45));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoCircleArcCommand(12, 24, 0.45));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(10));
    // addSequential(new AutoResetEncoderCommand());
    // addSequential(new AutoDriveCommand(15));
    // addSequential(new AutoDriveCommand(120));
    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
