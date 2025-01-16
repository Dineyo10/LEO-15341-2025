package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@Autonomous
public class High_Rung_Park extends LinearOpMode {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor left_back;
    private DcMotor right_back;
    private Servo grab;
    private Servo leftgrab;
    private Servo rightgrab;
    private DcMotor cap;
    private DcMotor cap2;
    private DcMotor activeArm1;
    private DcMotor activeArm2;
    private Servo arm1;
    private Servo arm2;
    private Servo wrist1;
    private Servo wrist2;
    private Servo swivel;
//    boolean arm = true;
//    float height;

    @Override
    public void runOpMode() {


        left_drive = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class, "right_back");

        grab = hardwareMap.get(Servo.class, "grab");

        leftgrab = hardwareMap.get(Servo.class, "leftgrab");
        rightgrab = hardwareMap.get(Servo.class, "rightgrab");


        cap = hardwareMap.get(DcMotor.class, "cap");
        cap2 = hardwareMap.get(DcMotor.class, "cap2");

        activeArm1= hardwareMap.get(DcMotor.class, "activeArm1");
        activeArm2 = hardwareMap.get(DcMotor.class, "activeArm2");

        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        wrist1 = hardwareMap.get(Servo.class, "front1");
        wrist2 = hardwareMap.get(Servo.class, "front2");


        swivel = hardwareMap.get(Servo.class, "swivel");


        right_back.setDirection(DcMotor.Direction.REVERSE);
        //right_drive is also reversed at line 325 and doesn't need to be reversed
//        right_drive.setDirection(DcMotor.Direction.REVERSE);


        cap.setDirection(DcMotor.Direction.REVERSE);

        activeArm1.setDirection(DcMotor.Direction.REVERSE);

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

//        left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        activeArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        activeArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        swivelSet();
        grab();
//         boolean pressed = false;
        waitForStart();

        // Put run blocks here.
        while (opModeIsActive()) {

//            sleep(4000);
            FirstMove();
            sleep(2000);
            resetEncoder();
            sleep(100);
//            Arm_out();
//            sleep(1000);
            forward_arm_front();
            sleep(1000);
            forward_arm_back();
            sleep(3000);
            transfer();
            sleep(500);
            forward_arm_front();
            sleep(800);
//            Arm_in();
//            sleep(1000);
            highRung();
            sleep(2500);
            grab();
            sleep(1000);
            smallback();
            sleep(1000);
            resetEncoder();
            sleep(100);
            bottom();
            sleep(1000);
            forward_arm_back();
            sleep(1000);
            obserePark();
            sleep(5000);
            LastMove();
            sleep(3000);
//            ToSample();
//            sleep(3000);
//            resetEncoder();
//            sleep(500);
//            back_arm_down();
//            sleep(1000);
//            transfer();
//            sleep(500);
//            forward_arm_front();
//            sleep(1000);
//            SpinToBasket();
//            sleep(1000);
//            resetEncoder();
//            sleep(500);
//            highBasket();
//            sleep(3000);
//            toBasket();
//            sleep(1000);
//            grab();
//            sleep(500);
//            smallforward();
//            sleep(600);
//            bottom();
//            sleep(1000);
//            obserePark();
//            sleep(5500);
//            resetEncoder();
//            sleep(500);
//            LastMove();
//            sleep(4000);
            Stop();
            sleep(3000000);

            telemetry.update();

            telemetry.addData("RCap", cap.getCurrentPosition());
            telemetry.addData("LCap2", cap2.getCurrentPosition());

//            telemetry.addData("height", height);
            telemetry.addData("RMotor", right_back.getCurrentPosition());
            telemetry.addData("LMotor", left_back.getCurrentPosition());

            telemetry.addData("FRMotor", right_drive.getCurrentPosition());
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
    public void grab(){
        grab.setPosition(0);
        leftgrab.setPosition(0.72);
        rightgrab.setPosition(0.33);
    }
    public void transfer(){
        leftgrab.setPosition(.33);
        rightgrab.setPosition(.65);
        sleep(200);
        grab.setPosition(1);
    }
    public void forward_arm_front(){
        wrist1.setPosition(.65);
        wrist2.setPosition(0.31);
        swivel.setPosition(0.5);
        arm1.setPosition(0.48);
        arm2.setPosition(0.49);
    }
    public void forward_arm_back(){
        wrist1.setPosition(0.31);
        wrist2.setPosition(.64);
        swivel.setPosition(.46);
        arm1.setPosition(.69);
        arm2.setPosition(0.28);
    }
    public void FirstMove(){
        left_back.setPower(.3);
        right_back.setPower(.3);
        left_drive.setPower(.3);
        right_drive.setPower(.3);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(850);
        right_back.setTargetPosition(850);
        left_drive.setTargetPosition(850);
        right_drive.setTargetPosition(-850);
    }
    public void SpinToBasket(){
        left_back.setPower(.7);
        right_back.setPower(.7);
        left_drive.setPower(.7);
        right_drive.setPower(.7);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(2450);
        right_back.setTargetPosition(-2450);
        left_drive.setTargetPosition(2450);
        right_drive.setTargetPosition(2450);
    }
    public void Arm_out(){
        activeArm1.setPower(.5);
        activeArm2.setPower(.5);
        activeArm1.setTargetPosition(-920);
        activeArm2.setTargetPosition(-920);
        activeArm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        activeArm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void Arm_in(){
        activeArm1.setPower(.5);
        activeArm2.setPower(.5);
        activeArm1.setTargetPosition(0);
        activeArm2.setTargetPosition(0);
        activeArm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        activeArm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void ToSample() {
        left_back.setPower(.3);
        right_back.setPower(.3);
        left_drive.setPower(.3);
        right_drive.setPower(.3);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(2200);
        right_back.setTargetPosition(-2200);
        left_drive.setTargetPosition(-2200);
        right_drive.setTargetPosition(-2200);
    }
    public void back_arm_down(){
        arm1.setPosition(0.12);
        arm2.setPosition(0.85);
    }
    public void LastMove(){
        left_back.setPower(.2);
        right_back.setPower(.2);
        left_drive.setPower(.2);
        right_drive.setPower(.2);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(-775);
        right_back.setTargetPosition(-775);
        left_drive.setTargetPosition(-775);
        right_drive.setTargetPosition(775);
    }
    public void highBasket() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-3150);
        cap2.setTargetPosition(-3150);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void highRung() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-920);
        cap2.setTargetPosition(-920);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void bottom() {
        cap.setPower(.6);
        cap2.setPower(.6);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap.setTargetPosition(0);
        cap2.setTargetPosition(0);
    }
    public void resetEncoder() {
        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void smallback(){
        left_back.setPower(.2);
        right_back.setPower(.2);
        left_drive.setPower(.2);
        right_drive.setPower(.2);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(-385);
        right_back.setTargetPosition(-385);
        left_drive.setTargetPosition(-385);
        right_drive.setTargetPosition(385);
    }
    public void smallforward(){
        left_back.setPower(.2);
        right_back.setPower(.2);
        left_drive.setPower(.2);
        right_drive.setPower(.2);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(-385);
        right_back.setTargetPosition(-385);
        left_drive.setTargetPosition(-385);
        right_drive.setTargetPosition(385);
    }
    public void toBasket(){
        left_back.setPower(.2);
        right_back.setPower(.2);
        left_drive.setPower(.2);
        right_drive.setPower(.2);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(445);
        right_back.setTargetPosition(445);
        left_drive.setTargetPosition(445);
        right_drive.setTargetPosition(-445);
    }

    //    public void armBack(){
//        arm1.setPosition(1);
//        arm2.setPosition(0);
//    }
    public void swivelSet(){
        swivel.setPosition(.5);
    }
    public void obserePark() {
        left_back.setPower(.3);
        right_back.setPower(.3);
        left_drive.setPower(.3);
        right_drive.setPower(.3);

        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back.setTargetPosition(-3800);
        right_back.setTargetPosition(2200);
        left_drive.setTargetPosition(2200);
        right_drive.setTargetPosition(3800);
    }
}
