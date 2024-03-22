package org.firstinspires.ftc.teamcode.CodeTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ForwardTest extends LinearOpMode {

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;

    public void runOpMode() {

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        //ToDo: Set Reverse Direction Motors
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double ly;
        double rx;

        waitForStart();
        if (opModeIsActive()) {

            while (opModeIsActive()) {

                ly = gamepad1.left_stick_y;
                rx = gamepad1.right_stick_x;

                if (ly > .2 || ly < -.2 || rx > .2 || rx < -.2) {
                    frontLeftMotor.setPower(ly + -rx);
                    frontRightMotor.setPower(ly + rx);
                    backLeftMotor.setPower(ly + -rx);
                    backRightMotor.setPower(ly + rx);
                } else {
                    frontLeftMotor.setPower(0);
                    frontRightMotor.setPower(0);
                    backLeftMotor.setPower(0);
                    backRightMotor.setPower(0);
                }

            }
        }
    }
}
