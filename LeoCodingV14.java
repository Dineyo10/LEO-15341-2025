package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp
public class LeoCodingV14 extends LinearOpMode {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor left_back;
    private DcMotor right_back;
    private Servo leftgrab;
    private Servo rightgrab;
    private DcMotor cap;
    private DcMotor cap2;
//    private DcMotor activeArm1;
//    private DcMotor activeArm2;
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

//        activeArm1= hardwareMap.get(DcMotor.class, "activeArm1");
//        activeArm2 = hardwareMap.get(DcMotor.class, "activeArm2");

        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");



//        drone = hardwareMap.get(Servo.class, "drone");


        right_back.setDirection(DcMotor.Direction.REVERSE);
        //right_drive is also reversed at line 325 and doesn't need to be reversed
//        right_drive.setDirection(DcMotor.Direction.REVERSE);


        cap.setDirection(DcMotor.Direction.REVERSE);
//        cap2.setDirection(DcMotor.Direction.REVERSE);


        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


//        left_back.setTargetPosition(0);
//        right_back.setTargetPosition(0);

//        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        boolean pressed = false;
        waitForStart();

        // Put run blocks here.
        while (opModeIsActive()) {

//drone
//fire
//            if (gamepad2.right_stick_button) {
//                drone.setPosition(0.7);
//            }
//
////ready
//            if (gamepad2.left_stick_button) {
//                drone.setPosition(1);
//            }

            //grab
            //grabbing
            if (gamepad2.x) {
                leftgrab.setPosition(0.72);
                rightgrab.setPosition(0.35);
            }

            //triangle/y is open
            //leftgrab up is open
            //rightgrab down is open
            if (gamepad2.y ) {
                leftgrab.setPosition(.38);
                rightgrab.setPosition(.62);

            }


            //open leftgrab

//            if (gamepad2.dpad_left) {
//                leftgrab.setPosition(0.85);
//            }
//            //open rightgrab
//            if (gamepad2.dpad_right) {
//                rightgrab.setPosition(0.15);
//            }


                        if (gamepad2.dpad_down) {
                            arm1.setPosition(0.5);
                            arm2.setPosition(0.5);
                        }
            //arm ready for pixel/sample
// arm down
            if (gamepad2.a ) {
//                wrist1.setPosition(.55);
//                wrist2.setPosition(0.45);
                arm1.setPosition(0.42);//90 degrees back | 0.0 //60 degress .34
                arm2.setPosition(0.58);//90 degrees back | 0.8


            }


            //arm ready to score
            //over
            if (gamepad2.b ) {
                arm1.setPosition(1);
                arm2.setPosition(0);
            }

            //arm straight up
            if (gamepad2.dpad_up) {

                arm1.setPosition(.79);
                arm2.setPosition(0.21);
            }


            // mecanum code
            if (gamepad1.dpad_down) {
                pressed = true;
            } else if (gamepad1.dpad_up) {
                pressed = false;
            }
            if (!pressed) {

                left_drive.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * .9);
                right_drive.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * .9);
                left_back.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * .9);
                right_back.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * .9);

            } else {
                left_drive.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * .35);
                right_drive.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * .35);
                left_back.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * .35);
                right_back.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * .35);
            }



            if(gamepad2.back){
                cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

//            if (gamepad2.right_bumper) {
//                cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                cap.setPower(1);
//                cap2.setPower(1);
//
////                cap.setTargetPosition(600);
////                cap2.setTargetPosition(600);
//
//                cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//                cap.setTargetPosition(600);
//                cap2.setTargetPosition(600);
//
//            }

//          if  (gamepad2.start){
//              cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//              cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//          }

//            if(gamepad2.left_bumper && !arm){
//                height=height+1;
//                arm=true;
//            }
//            if(gamepad2.right_bumper && arm){
//                height=height-1;
//                arm=false;
//            }
//            if (gamepad1.left_bumper && arm==false||gamepad2.left_bumper && arm==false) {
//
//            arm=true;
//
//            }
//
//            if (gamepad1.left_bumper && arm==true||gamepad2.left_bumper && arm== true) {
//
//                arm=false;
//
//            }
//
//
//            if (arm== true) {
//
//
//            if (activeArm1.getCurrentPosition() < 2100) {
//                activeArm1.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
//                activeArm2.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
//            }
//else
//{
////                double armpower = .1;
//                activeArm1.setPower(-Math.abs(gamepad2.left_trigger - gamepad2.right_trigger));
//                activeArm2.setPower(-Math.abs(gamepad2.left_trigger - gamepad2.right_trigger));
//
//
//            }

                if (cap.getCurrentPosition() < 3050) {


                    cap.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
                    cap2.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
//                    cap.setPower(gamepad2.right_trigger - gamepad1.left_trigger);
//                    cap2.setPower(gamepad2.right_trigger - gamepad1.left_trigger);



                }
                else {
                    cap.setPower(0);//-gamepad2.left_trigger - gamepad2.right_trigger);
                    cap2.setPower(0);//-gamepad2.left_trigger - gamepad2.right_trigger);
                }
//            }
//
//
//
//            if (arm==false) {
//
//
//                if (cap.getCurrentPosition() < 2800) {

            //hayden controls


//                } else {
//                    cap.setPower(0);//-gamepad2.left_trigger - gamepad2.right_trigger);
//                    cap2.setPower(0);//-gamepad2.left_trigger - gamepad2.right_trigger);
//                }
//            }
            telemetry.update();

            telemetry.addData("RCap", cap.getCurrentPosition());
            telemetry.addData("Lcap2", cap2.getCurrentPosition());

//            telemetry.addData("rightarm", activeArm1.getCurrentPosition());
//            telemetry.addData("leftarm", activeArm2.getCurrentPosition());

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
}
