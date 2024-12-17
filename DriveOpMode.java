package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveOpMode extends LinearOpMode {
    private DcMotor LF;
    private DcMotor RF;
    private DcMotor LB;
    private DcMotor RB;

    @Override
    public void runOpMode() throws InterruptedException {
        LF = hardwareMap.get(DcMotor.class, "LF");
        RF = hardwareMap.get(DcMotor.class, "RF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RB = hardwareMap.get(DcMotor.class, "RB");

        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        LF.setDirection(DcMotor.Direction.REVERSE);
//        RF.setDirection(DcMotor.Direction.REVERSE);
//        LB.setDirection(DcMotor.Direction.REVERSE);
//        RB.setDirection(DcMotor.Direction.REVERSE);

        while (opModeIsActive()){
         LF.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
         RF.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
         LB.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
         RB.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);






        }
    }
}
