package org.firstinspires.ftc.teamcode.CodeTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class FieldCentricDrive2 extends LinearOpMode{

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;

    public void runOpMode() {

        //Get Motor Names
        //ToDo: Set the Motor Names
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        //Set the Motors to run using the Encoders
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Get the IMU from the HardwareMap
        //ToDo: Set the IMU Name
        IMU imu = hardwareMap.get(IMU.class, "imu");

        //Get the Direction of the IMU
        //ToDo: Set the Direction of the IMU
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));

        //Initialize the IMU
        imu.initialize(parameters);

        //Define movement doubles
        double ly = gamepad1.left_stick_y;
        double lx = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        //Define the Bot Heading
        double botHeading  = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bots rotation
        double rotX = lx * Math.cos(-botHeading) - ly * Math.sin(-botHeading);
        double rotY = lx * Math.cos(-botHeading) + ly * Math.sin(-botHeading);

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                if (ly > .2 || ly < -.2 || lx > .2 || lx < -.2 || rx > .2 || rx < -.2) {
                    frontLeftMotor.setPower(frontLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backLeftMotor.setPower(backLeftPower);
                    backRightMotor.setPower(backRightPower);
                } else {
                    frontLeftMotor.setPower(0);
                    frontRightMotor.setPower(0);
                    backLeftMotor.setPower(0);
                    backRightMotor.setPower(0);
                }

                telemetry.addData("Robot Direction: ", botHeading);
                telemetry.addData("FrontLeftMotor: ", frontLeftMotor.getPower());
                telemetry.addData("BackLeftMotor: ", backLeftMotor.getPower());
                telemetry.addData("FrontRightMotor: ", frontRightMotor.getPower());
                telemetry.addData("BackRightMotor: ", backRightMotor.getPower());
                telemetry.update();
            }
        }
    }
}