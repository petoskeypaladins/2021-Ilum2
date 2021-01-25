/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TrenchRunGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  static public double DRIVE_TO_TRENCH = 70;
  static public double TURN_TO_FACE_CELLS = 15;
  static public double DRIVE_THROUGH_TRENCH = 80;
  public TrenchRunGroup() {
    addSequential(new AutoGroup()); //runs the standard Auto procedure
    addSequential(new AutoCollectCommand(-0.7)); //turns on the intake wheels
    addSequential(new AutoDriveCommand(DRIVE_TO_TRENCH, 0.65)); //drives to the trench as the intake goes out
    addSequential(new AutoTurnCommand(TURN_TO_FACE_CELLS)); //turns to face the row of cells
    addSequential(new AutoDriveCommand(DRIVE_THROUGH_TRENCH, 0.45)); //drives through and collects all three cells
    // addSequential(new FillFeederCommand(true));
    addSequential(new AutoCollectCommand(0)); //turns off the intake wheels
    addSequential(new AutoDriveCommand(-DRIVE_THROUGH_TRENCH - 20, 0.65)); //drives back to the shooting location
    addSequential(new AutoTurnCommand(-TURN_TO_FACE_CELLS));
    // addSequential(new AutoDriveCommand(-DRIVE_TO_TRENCH, 0.3));
    addSequential(new AutoShootCommand(0.75));

    addSequential(new AutoWaitCommand(), .1);
    addSequential(new AutoFireCommand());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

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
