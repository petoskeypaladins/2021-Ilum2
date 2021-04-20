// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCircleGroupTest2 extends CommandGroup {
  /** Add your docs here. */
  public AutoCircleGroupTest2() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    addSequential(new AutoDriveCommand(18));
    addSequential(new AutoCircleCommand(0.61, 90, false));
    addSequential(new AutoCircleCommand(0.54, 85, true));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(101));
    addSequential(new AutoCircleCommand(0.54, 90, true));
    addSequential(new AutoCircleCommand(0.56, 265, false));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(270));

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
