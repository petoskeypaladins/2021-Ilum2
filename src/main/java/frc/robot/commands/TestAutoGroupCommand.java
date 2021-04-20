// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutoGroupCommand extends CommandGroup {
  /** Add your docs here. */
  public TestAutoGroupCommand() {
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(95));
    addSequential(new AutoCircleCommand(0.56, 357, true));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(98));
    addSequential(new AutoCircleCommand(0.56, 310, false));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(65));
    addSequential(new AutoCircleCommand(0.56, 225, false));
    addSequential(new AutoResetEncoderCommand());
    addSequential(new AutoDriveCommand(230));
    // addSequential(new AutoCircleTestCommand());



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
