package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import java.util.Locale;

@Autonomous(name="1 Specimen+3 Samples", group="Auto")
//@Disabled

public class OnePlusThreeAuto extends LinearOpMode {

    DcMotor leftFrontDrive;
    DcMotor rightFrontDrive;
    DcMotor leftBackDrive;
    DcMotor rightBackDrive;
    private Servo grab;
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
    GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer
    DriveToPoint nav = new DriveToPoint(this); //OpMode member for the point-to-point navigation class

    enum StateMachine {
        WAITING_FOR_START,
        AT_TARGET,
        DRIVE_TO_TARGET_1,
        DRIVE_TO_TARGET_2,
        DRIVE_TO_TARGET_3,
        DRIVE_TO_TARGET_3_5,
        DRIVE_TO_TARGET_4,
        DRIVE_TO_TARGET_5,
        DRIVE_TO_TARGET_6,
        DRIVE_TO_TARGET_7,
        DRIVE_TO_TARGET_8,
        DRIVE_TO_TARGET_8_5,
        DRIVE_TO_TARGET_9,
        DRIVE_TO_TARGET_10,
        DRIVE_TO_TARGET_11,
        DRIVE_TO_TARGET_12,
        DRIVE_TO_TARGET_13,
        DRIVE_TO_TARGET_14,
        DRIVE_TO_TARGET_15,
        DRIVE_TO_TARGET_16,
        DRIVE_TO_TARGET_17,
        DRIVE_TO_TARGET_18,
        DRIVE_TO_TARGET_19,
        DRIVE_TO_TARGET_20,
        DRIVE_TO_TARGET_5_33,
        DRIVE_TO_TARGET_5_66,

    }

    static final Pose2D TARGET_1 = new Pose2D(DistanceUnit.MM,0,-300, AngleUnit.DEGREES,90);
    static final Pose2D TARGET_2 = new Pose2D(DistanceUnit.MM, 0, -780+40, AngleUnit.DEGREES, 90);
    static final Pose2D TARGET_3 = new Pose2D(DistanceUnit.MM,450,-300, AngleUnit.DEGREES,90);
//    static final Pose2D TARGET_3_5 = new Pose2D(DistanceUnit.MM,450,-300, AngleUnit.DEGREES,-90);
static final Pose2D TARGET_4 = new Pose2D(DistanceUnit.MM, 975, -590, AngleUnit.DEGREES, -90);
    static final Pose2D TARGET_5 = new Pose2D(DistanceUnit.MM, 1140, -340+20, AngleUnit.DEGREES, -135);
    static final Pose2D TARGET_6 = new Pose2D(DistanceUnit.MM, 1200,-590, AngleUnit.DEGREES, -90);
    static final Pose2D TARGET_7 = new Pose2D(DistanceUnit.MM, 1110, -340+20, AngleUnit.DEGREES, -135);
    static final Pose2D TARGET_8 = new Pose2D(DistanceUnit.MM, 1160, -960, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_8_5 = new Pose2D(DistanceUnit.MM, 1100, -970, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_9 = new Pose2D(DistanceUnit.MM, 1120, -330+20, AngleUnit.DEGREES, -135);
    static final Pose2D TARGET_10 = new Pose2D(DistanceUnit.MM, 900, -1300, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_11 = new Pose2D(DistanceUnit.MM, 300, -1300, AngleUnit.DEGREES, 0);

    static final Pose2D TARGET_12 = new Pose2D(DistanceUnit.MM, -780+40, -250, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_13 = new Pose2D(DistanceUnit.MM, -200, 770, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_14 = new Pose2D(DistanceUnit.MM, -300, -150, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_15 = new Pose2D(DistanceUnit.MM, -780+40, -325, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_16 = new Pose2D(DistanceUnit.MM, -200, 1100, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_17 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_18 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_19 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_20 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);


    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftBackDrive   = hardwareMap.get(DcMotor.class, "left_back");
        rightBackDrive  = hardwareMap.get(DcMotor.class, "right_back");
        grab = hardwareMap.get(Servo.class, "grab");

        backGrab = hardwareMap.get(Servo.class, "backGrab");


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

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        activeArm1.setDirection(DcMotor.Direction.REVERSE);
        activeArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        activeArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap.setDirection(DcMotor.Direction.REVERSE);

        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-187.5, -3); // //these are tuned for 3110-0002-0001 Product Insight #1
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();

        //nav.setXYCoefficients(0.02,0.002,0.0,DistanceUnit.MM,12);
        //nav.setYawCoefficients(1,0,0.0, AngleUnit.DEGREES,2);
        nav.setDriveType(DriveToPoint.DriveType.MECANUM);

        StateMachine stateMachine;
        stateMachine = StateMachine.WAITING_FOR_START;

        telemetry.addData("Status", "Initialized");
        telemetry.addData("X offset", odo.getXOffset());
        telemetry.addData("Y offset", odo.getYOffset());
        telemetry.addData("Device Version Number:", odo.getDeviceVersion());
        telemetry.addData("Device Scalar", odo.getYawScalar());
        telemetry.update();
//        swivelSet();
        transfer();
        // Wait for the game to start (driver presses START)
        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {
            odo.update();

            switch (stateMachine){
                case WAITING_FOR_START:
                    //the first step in the autonomous

                    stateMachine = StateMachine.DRIVE_TO_TARGET_1;
                    break;
                case DRIVE_TO_TARGET_1:
                    /*
                    drive the robot to the first target, the nav.driveTo function will return true once
                    the robot has reached the target, and has been there for (holdTime) seconds.
                    Once driveTo returns true, it prints a telemetry line and moves the state machine forward.
                     */
                    if (nav.driveTo(odo.getPosition(), TARGET_1, 1, 0)){
                        highRung();
                        back_arm_bar();
                        sleep(1000);
                        telemetry.addLine("at position #1!");

                        stateMachine = StateMachine.DRIVE_TO_TARGET_2;
                    }
                    break;
                case DRIVE_TO_TARGET_2:
                    //drive to the second target
                    if (nav.driveTo(odo.getPosition(), TARGET_2, 1, 0)){
                        grab();
                        sleep(200);
                        bottom();
                        forward_arm_back();
//                        forward_arm_up();

//                        sleep(300);
                        telemetry.addLine("at position #2!");
                        stateMachine = StateMachine.DRIVE_TO_TARGET_3;
                    }
                    break;
                case DRIVE_TO_TARGET_3:
                    if(nav.driveTo(odo.getPosition(), TARGET_3, 1, 0.1)){
                        telemetry.addLine("at position #3");
                        transfer();
                        forward_arm_front();
                        Arm_out();
//                        open_less();
//                        swivelZero();
//                        sleep(500);


                        stateMachine = StateMachine.DRIVE_TO_TARGET_4;
                    }
                    break;
//                case DRIVE_TO_TARGET_3_5:
//                    if(nav.driveTo(odo.getPosition(), TARGET_3_5, 1, .2)){
//                        telemetry.addLine("at position #3");
////                        transfer();
////                        forward_arm_front();
//
////                        swivelZero();
////                        sleep(500);
//
//
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_4;
//                    }
//                    break;
                case DRIVE_TO_TARGET_4:
                    if(nav.driveTo(odo.getPosition(),TARGET_4,1,.3)){
                        telemetry.addLine("at position #4");
                        grab();
                        sleep(300);
                        forward_arm_back();
                        Arm_in();
                        sleep(700);
                        transfer();
                        sleep(400);

                        stateMachine = StateMachine.DRIVE_TO_TARGET_5;
                    }
                    break;
                case DRIVE_TO_TARGET_5:
                    highBasket();

                    if(nav.driveTo(odo.getPosition(),TARGET_5,1,.5)){
                        telemetry.addLine("at position #5!");
//                        sleep(1200);
                        forward_arm_front();
                        sleep(800);
                        grab();
                        sleep(300);
                        forward_arm_back();
                        sleep(100);
                        bottom();
                        transfer();
                        Arm_out();
//                        open_less();
//                        sleep(1000);
                        stateMachine = StateMachine.DRIVE_TO_TARGET_6;
                    }
                    break;
                case DRIVE_TO_TARGET_6:
                    forward_arm_front();
                    if(nav.driveTo(odo.getPosition(), TARGET_6, 1, .5)){
                        telemetry.addLine("at position #6");
//                        sleep(500);
                        grab();
                        sleep(300);
                        Arm_in();
                        forward_arm_back();
                        sleep(600);
                        transfer();
                        sleep(400);
                        stateMachine = StateMachine.DRIVE_TO_TARGET_7;
                    }
                    break;
                case DRIVE_TO_TARGET_7:
                    highBasket();
                    if(nav.driveTo(odo.getPosition(), TARGET_7, 1, .4)){
                        telemetry.addLine("at position #7");
                        forward_arm_front();
                        sleep(400);
                        grab();
                        sleep(300);
                        forward_arm_back();
                        sleep(100);
                        bottom();
                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_8;
                        forward_arm_front();
                        swivelright();
                    }
                    break;
                case DRIVE_TO_TARGET_8:

                    if(nav.driveTo(odo.getPosition(), TARGET_8, 1, .4)){
                        telemetry.addLine("at position #8");
                        grab();
                        sleep(300);


                        stateMachine = StateMachine.DRIVE_TO_TARGET_8_5;

                    }
                    break;
                case DRIVE_TO_TARGET_8_5:
                    if(nav.driveTo(odo.getPosition(), TARGET_8_5, 1, 0.1)){
                        telemetry.addLine("at position #8");
                        forward_arm_back();
                        sleep(600);
                        transfer();
                        sleep(200);

                        stateMachine = StateMachine.DRIVE_TO_TARGET_9;

                    }
                    break;
                case DRIVE_TO_TARGET_9:
                    highBasket();
                    if(nav.driveTo(odo.getPosition(), TARGET_9, 1, .4)){
//                        sleep(2000);
                        forward_arm_front();
                        sleep(600);
                        grab();
                        sleep(300);
                        forward_arm_back();
                        sleep(100);
                        bottom();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_10;
                    }
                    break;
                case DRIVE_TO_TARGET_10:
//                    sleep(1500);
                    if(nav.driveTo(odo.getPosition(), TARGET_10, 1, 0)){
                        telemetry.addLine("at position #10");
                    forward_arm_front();


//                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_11;
                    }
                    break;
                case DRIVE_TO_TARGET_11:
                    if(nav.driveTo(odo.getPosition(), TARGET_11, 1, 0)){
                        telemetry.addLine("at position #11");


                        stateMachine = StateMachine.AT_TARGET;
                    }
//                    break;
//                case DRIVE_TO_TARGET_12:
//                    if(nav.driveTo(odo.getPosition(), TARGET_12, 1, 0)){
//
//                        grab();
//                        sleep(200);
//                        forward_arm_upper();
//                        bottom();
////                        sleep(300);
//                        transfer();
//                        telemetry.addLine("at position #12");
////                        transfer();
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_13;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_13:
//                    if(nav.driveTo(odo.getPosition(), TARGET_13, 1, 0)){
//                        forward_arm_up();
//                        sleep(80);
//                        grab();
//                        sleep(350);
//                        swivelSet();
////                        sleep(100);
//                        forward_arm_back();
//                        sleep(300);
//                        telemetry.addLine("at position #13");
////                        transfer();
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_14;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_14:
//                    if(nav.driveTo(odo.getPosition(), TARGET_14, 1, 0)){
//                        telemetry.addLine("at position #14");
//
//                        transfer();
////                        transfer();
////                        back_arm_bar();
//                        back_arm_bar();
//                        highRung();
//                        sleep(500);
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_15;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_15:
//                    if(nav.driveTo(odo.getPosition(), TARGET_15, 1, 0)){
//
//                        grab();
//                        sleep(200);
////                        forward_arm_front();
//
//                        telemetry.addLine("at position #15");
////                        transfer();
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_16;
//                        forward_arm_back();
//                        bottom();
////                        sleep(300);
//                        transfer();
//
//                    }
//                    break;
//                case DRIVE_TO_TARGET_16:
//                    if(nav.driveTo(odo.getPosition(), TARGET_16, 1, 0)){
//                        telemetry.addLine("at position #16");
////                        transfer();
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_17;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_17:
//                    if(nav.driveTo(odo.getPosition(), TARGET_16, 1, 0)){
//                        telemetry.addLine("at position #16");
////                        transfer();
////                        stateMachine = StateMachine.DRIVE_TO_TARGET_18;
//                    }
//                    break;
            }


            //nav calculates the power to set to each motor in a mecanum or tank drive. Use nav.getMotorPower to find that value.
            leftFrontDrive.setPower(nav.getMotorPower(DriveToPoint.DriveMotor.LEFT_FRONT));
            rightFrontDrive.setPower(nav.getMotorPower(DriveToPoint.DriveMotor.RIGHT_FRONT));
            leftBackDrive.setPower(nav.getMotorPower(DriveToPoint.DriveMotor.LEFT_BACK));
            rightBackDrive.setPower(nav.getMotorPower(DriveToPoint.DriveMotor.RIGHT_BACK));

            telemetry.addData("current state:",stateMachine);

            Pose2D pos = odo.getPosition();
            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.MM), pos.getY(DistanceUnit.MM), pos.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Position", data);

            telemetry.update();

        }
    }
    public void open_less(){
        grab.setPosition(0.5);

    }
    public void grab(){
        backGrab.setPosition(.8);
        grab.setPosition(0.27);
    }
    public void transfer(){
        backGrab.setPosition(.5);
        sleep(150);
        grab.setPosition(.8);
    }
    public void reverseTransfer(){
        grab.setPosition(1);
        sleep(150);
        backGrab.setPosition(.5);

    }
    public void forward_arm_up(){
        swivel.setPosition(0);
        wrist1.setPosition(.62);
        wrist2.setPosition(0.37);

    }
    public void forward_arm_upper(){
        swivel.setPosition(0);
        wrist1.setPosition(.55);
        wrist2.setPosition(0.47);

    }
    public void forward_arm_front(){
        wrist.setPosition(.63);

        wrist1.setPosition(.91);
        wrist2.setPosition(0.08);
        swivel.setPosition(.68);
        arm1.setPosition(0.53);
        arm2.setPosition(.49);

    }
    public void forward_arm_back(){
        wrist.setPosition(0);

        wrist1.setPosition(0.06);
        wrist2.setPosition(.91);
        swivel.setPosition(.64);
//        sleep(100);
        arm1.setPosition(.67);
        arm2.setPosition(0.35);
    }
    public void Arm_out(){
        activeArm1.setPower(.8);
        activeArm2.setPower(.8);
        activeArm1.setTargetPosition(-480);
        activeArm2.setTargetPosition(-480);
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
    public void wristdown(){
        wrist.setPosition(.6);
    }
    public void back_arm_bar(){
        wrist.setPosition(.32);
        arm1.setPosition(0.32);
        arm2.setPosition(.67);
    }
    public void back_arm_down(){
        arm1.setPosition(0.10);
        arm2.setPosition(0.90);
        wrist.setPosition(.2);
    }
    public void highBasket() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-3050);
        cap2.setTargetPosition(-3050);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void highRung() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-880);
        cap2.setTargetPosition(-880);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void highRungUP() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-2500);
        cap2.setTargetPosition(-2500);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void highRungLower() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-520);
        cap2.setTargetPosition(-520);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void bottom() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(0);
        cap2.setTargetPosition(0);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void swivelSet(){
        swivel.setPosition(0.68);
    }
    public void swivelZero(){
        swivel.setPosition(0);
    }
    public void swivelright(){
        swivel.setPosition(1);
    }

}