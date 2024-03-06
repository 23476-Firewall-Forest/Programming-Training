package org.firstinspires.ftc.teamcode.CodeTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ArmExtend extends LinearOpMode{

    public DcMotor Extend;

    @Override
    public void runOpMode() {
        if (gamepad1.dpad_up) {
            MotorRotate(1);
        }

    }

    public void MotorRotate(int rotations) {
        Extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int MotorPosition = Extend.getCurrentPosition();
        int MotorTicks = 1440 * rotations;

        for(int r = MotorPosition; r > MotorTicks; r-=10) {
            Extend.setTargetPosition(r);
        } for (int r = MotorPosition; r < MotorTicks; r+=10) {
            Extend.setTargetPosition(r);
        }

    }
}