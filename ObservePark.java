package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@Autonomous
public class ObservePark extends LinearOpMode {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor left_back;
    private DcMotor right_back;
    private Servo leftgrab;
    private Servo rightgrab;
    private DcMotor cap;
    private DcMotor cap2;
    private Servo arm1;
    private Servo arm2;
    private Servo wrist1;
    private Servo wrist2;
    private Servo drone;
//    boolean arm = true;
//    float height;

    @Override
    public void runOpMode() {


        left_drive = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class, "right_back");


        leftgrab = hardwareMap.get(Servo.class, "leftgrab");
        rightgrab = hardwareMap.get(Servo.class, "rightgrab");


        cap = hardwareMap.get(DcMotor.class, "cap");
        cap2 = hardwareMap.get(DcMotor.class, "cap2");

        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");



        drone = hardwareMap.get(Servo.class, "drone");


        right_back.setDirection(DcMotor.Direction.REVERSE);
        //right_drive is also reversed at line 325 and doesn't need to be reversed
//        right_drive.setDirection(DcMotor.Direction.REVERSE);


        cap.setDirection(DcMotor.Direction.REVERSE);
//        cap2.setDirection(DcMotor.Direction.REVERSE);


//        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



//        left_back.setTargetPosition(0);
//        right_back.setTargetPosition(0);

//        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//         boolean pressed = false;
        waitForStart();

        // Put run blocks here.
        while (opModeIsActive()) {

        back();
        sleep(10000);
        Stop();
        sleep(3000000);
            telemetry.update();

            telemetry.addData("RCap", cap.getCurrentPosition());
            telemetry.addData("Lcap2", cap2.getCurrentPosition());

//            telemetry.addData("height", height);
            telemetry.addData("rMotor", right_back.getCurrentPosition());
            telemetry.addData("LMotor", left_back.getCurrentPosition());

            telemetry.addData("FrMotor", right_drive.getCurrentPosition());
            telemetry.addData("FLMotor", left_drive.getCurrentPosition());
//            telemetry.addData("pressed", pressed();
//
//            telemetry.addData("arm", arm);
            telemetry.update();

        }

    }
    public void forwardslow() {
        left_drive.setPower(-0.3);
        right_drive.setPower(.3);
        left_back.setPower(-0.3);
        right_back.setPower(-0.3);


//        leftgrab.setPosition(0.8);
//        rightgrab.setPosition(0);
    }
    public void forward() {
        left_drive.setPower(-0.6);
        right_drive.setPower(.6);
        left_back.setPower(-0.6);
        right_back.setPower(-0.6);


//        leftgrab.setPosition(0.8);
//        rightgrab.setPosition(0);
    }
    public void back() {
        left_drive.setPower(0.3);
        right_drive.setPower(-.3);
        left_back.setPower(0.3);
        right_back.setPower(0.3);


//        leftgrab.setPosition(0.8);
//        rightgrab.setPosition(0);
    }
    public void Stop() {
        left_drive.setPower(0);
        right_drive.setPower(0);
        left_back.setPower(0);
        right_back.setPower(0);

    }
}
