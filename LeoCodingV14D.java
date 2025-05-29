package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;



//@Disabled
@TeleOp
public class LeoCodingV14D extends LinearOpMode {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor left_back;
    private DcMotor right_back;
    private Servo grab;
    //    private Servo leftgrab;
//    private Servo rightgrab;
    private DcMotor cap;
    private DcMotor cap2;
    private DcMotor activeArm1;
    private DcMotor activeArm2;
    private Servo arm1;
    private Servo arm2;
    private Servo wrist1;
    private Servo wrist2;
    private Servo swivel;
    private TouchSensor touch;
    private ColorSensor color;
    private Servo backGrab;
    private Servo wrist;






    // Target heading for straight movement (0 degrees is "straight")
//    private DistanceSensor distance;
//    private Servo drone;
//    boolean arm = true;
//    float height;


    @Override
    public void runOpMode() {


        left_drive = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class, "right_back");

        grab = hardwareMap.get(Servo.class, "grab");

        backGrab = hardwareMap.get(Servo.class, "backGrab");

//        leftgrab = hardwareMap.get(Servo.class, "leftgrab");
//        rightgrab = hardwareMap.get(Servo.class, "rightgrab");


        cap = hardwareMap.get(DcMotor.class, "cap");
        cap2 = hardwareMap.get(DcMotor.class, "cap2");

        activeArm1= hardwareMap.get(DcMotor.class, "activeArm1");
        activeArm2 = hardwareMap.get(DcMotor.class, "activeArm2");

        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        wrist1 = hardwareMap.get(Servo.class, "front1");
        wrist2 = hardwareMap.get(Servo.class, "front2");

        wrist = hardwareMap.get(Servo.class, "wrist");


        swivel = hardwareMap.get(Servo.class, "swivel");

        touch = hardwareMap.get(TouchSensor.class, "touch");

//        color = hardwareMap.get(ColorSensor.class, "color");





//        distance = hardwareMap.get(DistanceSensor.class, "distance");

//        drone = hardwareMap.get(Servo.class, "drone");


        right_back.setDirection(DcMotor.Direction.REVERSE);
        //right_drive is also reversed at line 325 and doesn't need to be reversed
//        right_drive.setDirection(DcMotor.Direction.REVERSE);


        cap.setDirection(DcMotor.Direction.REVERSE);
//        cap2.setDirection(DcMotor.Direction.REVERSE);

        activeArm1.setDirection(DcMotor.Direction.REVERSE);
//        activeArm2.setDirection(DcMotor.Direction.REVERSE);

        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        activeArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        activeArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        activeArm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        activeArm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        left_back.setTargetPosition(0);
//        right_back.setTargetPosition(0);

//        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        YawPitchRollAngles robotOrientation;

        boolean pressed = false;
        waitForStart();

        // Give time to display the message before starting the loop
        // Put run blocks here.
        while (opModeIsActive()) {

            float LF_Power=((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 1 );
            float RF_Power=((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
            float LB_Power=((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
            float RB_Power=((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * 1);


            if (gamepad2.x) {
                backGrab.setPosition(.9);
                grab.setPosition(0.27);

            }

            //triangle/y is open
            //leftgrab up is open
            //rightgrab down is open
            if (gamepad2.y ) {
                backGrab.setPosition(.52);
                sleep(150);
                grab.setPosition(1);

            }

            if (activeArm1.getCurrentPosition() > -1000 && gamepad1.a ) {
                arm1.setPosition(0.12);
                arm2.setPosition(0.90);
                wrist.setPosition(.6);
            }
            if(gamepad1.b){
            arm1.setPosition(0.12);
            arm2.setPosition(0.90);
            wrist.setPosition(.3);
            }
            if (activeArm1.getCurrentPosition() < -1000){
                arm1.setPosition(0.63);
                arm2.setPosition(0.36);
                wrist.setPosition(.3);
            }


//            if(gamepad1.b){
//                wrist.setPosition(0);
//            }
//            if(gamepad1.y){
//                wrist.setPosition(1);
//            }


            if (gamepad2.a ) {
                //BETU code
                //axon wrist code
                //old claw
                wrist.setPosition(.63);

                wrist1.setPosition(.91);
                wrist2.setPosition(0.08);
                swivel.setPosition(.68);
                arm1.setPosition(0.53);
                arm2.setPosition(.49);

                //goBilda servo code
//                wrist1.setPosition(.89);
//                wrist2.setPosition(0.07);
//                swivel.setPosition(.68);
//                arm1.setPosition(0.39);
//                arm2.setPosition(0.61);
            }

            if (gamepad2.b ) {
                //BETU code
                //vertical handoff
                wrist.setPosition(0);

                wrist1.setPosition(0.06);
                wrist2.setPosition(.91);
                swivel.setPosition(.64);
                arm1.setPosition(.67);
                arm2.setPosition(0.35);

                //freaky ah auto handoff/specimen handoff
//                wrist.setPosition(.78);
//
//                wrist1.setPosition(0.21);
//                wrist2.setPosition(.78);
//                swivel.setPosition(.65);
//                sleep(200);
//                arm1.setPosition(.85);
//                arm2.setPosition(0.14);
                //normal handoff
//                wrist.setPosition(.55);
//
//                wrist1.setPosition(0.2);
//                wrist2.setPosition(.8);
//                swivel.setPosition(.66);
//                arm1.setPosition(.67);
//                arm2.setPosition(0.32);

                //goBilda code
//                wrist1.setPosition(0.36);
//                wrist2.setPosition(.59);
//                swivel.setPosition(.64);
//                arm1.setPosition(.83);
//                arm2.setPosition(0.17);
            }
            if(gamepad2.right_bumper){
                //specimen handoff
                wrist.setPosition(.76);

                wrist1.setPosition(0.20);
                wrist2.setPosition(.79);
                swivel.setPosition(.65);
                sleep(100);
                arm1.setPosition(.86);
                arm2.setPosition(0.16);
            }

            if(gamepad2.dpad_up){
                swivel.setPosition(.68);
                wrist1.setPosition(.77);
                wrist2.setPosition(0.2);
            }

            if(gamepad2.dpad_left){
                swivel.setPosition(1);
            }
            if(gamepad2.dpad_right){
                swivel.setPosition(.34);
            }
            if(gamepad2.dpad_down){
                swivel.setPosition(.68);
            }
            if(gamepad2.left_stick_button){
//                grab.setPosition(0.4);
//                sleep(100);
                wrist.setPosition(.78);
                swivel.setPosition(0);
                wrist1.setPosition(.62);
                wrist2.setPosition(0.37);
                arm1.setPosition(.88);
                arm2.setPosition(0.14);
            }

            if(gamepad2.right_stick_button){
//                grab.setPosition(0.4);
//                sleep(100);
//                swivel.setPosition(0);
                wrist.setPosition(.3);
                arm1.setPosition(0.32);
                arm2.setPosition(.67);
            }

            //arm straight up
//            if (gamepad2.dpad_up) {
//
//                arm1.setPosition(.79);
//                arm2.setPosition(0.21);
//            }


            // mecanum code
            if (gamepad1.dpad_down) {
                pressed = true;
            } else if (gamepad1.dpad_up) {
                pressed = false;
            }
            if (!pressed) {

                left_drive.setPower(LF_Power);
                right_drive.setPower(RF_Power);
                left_back.setPower(LB_Power);
                right_back.setPower(RB_Power);

            } else {
                left_drive.setPower((LF_Power) * .5);
                right_drive.setPower((RF_Power) * .5);
                left_back.setPower((LB_Power) * .5);
                right_back.setPower((RB_Power) * .5);
            }



            if(gamepad2.back){
                cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

//

            if (activeArm1.getCurrentPosition() > -1725) {
                activeArm1.setPower(gamepad1.left_trigger - gamepad1.right_trigger);
                activeArm2.setPower(gamepad1.left_trigger - gamepad1.right_trigger);
            }

            else {
                activeArm1.setPower(Math.abs(gamepad1.left_trigger - gamepad1.right_trigger));
                activeArm2.setPower(Math.abs(gamepad1.left_trigger - gamepad1.right_trigger));
            }






//if(gamepad1.b){
//    boolean w=true;
//}

            if (cap2.getCurrentPosition() > -3050 && !gamepad1.right_stick_button) {


                cap.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
                cap2.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            }
            else {
                cap.setPower(0);
                cap2.setPower(0);
            }

            if(gamepad1.right_stick_button){
//                cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                cap.setPower(1);
                cap2.setPower(1);
                cap.setTargetPosition(-845);
                cap2.setTargetPosition(-845);
                cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if(gamepad2.left_trigger+gamepad2.right_trigger>0){
                cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }


//            if(gamepad1.start){
//                cap.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                cap2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }
            if(gamepad2.start){
                cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            if(gamepad1.left_stick_button){
                activeArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                activeArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                activeArm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                activeArm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
//            if(touch.isPressed()){
//                cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            }
//            else{
//                cap.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                cap2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            }
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
            //disable for matches!!!
            telemetry.addData("RCap", cap.getCurrentPosition());
            telemetry.addData("Lcap2", cap2.getCurrentPosition());

            telemetry.addData("leftarm", activeArm1.getCurrentPosition());
            telemetry.addData("rightarm", activeArm2.getCurrentPosition());

//            telemetry.addData("rMotor", right_back.getCurrentPosition());
//            telemetry.addData("LMotor", left_back.getCurrentPosition());
//            telemetry.addData("FrMotor", right_drive.getCurrentPosition());
//            telemetry.addData("FLMotor", left_drive.getCurrentPosition());
//            telemetry.addData("lefttrigger", gamepad2.left_trigger);
//            telemetry.addData("righttrigger", gamepad2.right_trigger);

//            telemetry.addData("BlueValue", color.blue());
//            telemetry.addData("RedValue",  color.red());
//            telemetry.addData("GreenValue",  color.green());


//            telemetry.addData("TouchSensor", touch.isPressed());
//            telemetry.addData("distance", distance.getDistance(DistanceUnit.CM));
//            telemetry.addData("Yaw", robotOrientation.getYaw(AngleUnit.DEGREES));
//            telemetry.addData("Roll", robotOrientation.getRoll(AngleUnit.DEGREES));
//            telemetry.addData("Pitch", robotOrientation.getPitch(AngleUnit.DEGREES));

            telemetry.addData("pressed", touch.isPressed());
//

//            telemetry.addData("arm", arm);
            telemetry.update();

        }


    }




}

