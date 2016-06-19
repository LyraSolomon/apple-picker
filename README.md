**Simple code for apple-harvesting robot**



This project is meant to be build on Linux using the [Bazel](http://www.bazel.io) build system. The robot is wired and programmed the same way as an FRC robot, using [a Roborio controller and the WPILib library](https://wpilib.screenstepslive.com/s). Currently the robot is controlled manually with the [FRC driver station](https://wpilib.screenstepslive.com/s/4485/m/24192) for prototyping.

The roborio address is Roborio-1678-FRC.local. Code is compiled by running `bazel build //frc1678 --cpu=roborio`. Code is deployed by running `./deploy`.