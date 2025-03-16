package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@Autonomous(name="3SpecimenAuto", group="Auto")
@Disabled

public class ThreeSpecimenAutoV3 extends LinearOpMode {

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
        DRIVE_TO_TARGET_4,
        DRIVE_TO_TARGET_5,
        DRIVE_TO_TARGET_6,
        DRIVE_TO_TARGET_7,
        DRIVE_TO_TARGET_8,
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

    static final Pose2D TARGET_1 = new Pose2D(DistanceUnit.MM,-300,0,AngleUnit.DEGREES,0);
    static final Pose2D TARGET_2 = new Pose2D(DistanceUnit.MM, -780+50, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_3 = new Pose2D(DistanceUnit.MM,-100,400, AngleUnit.DEGREES,0);
    static final Pose2D TARGET_4 = new Pose2D(DistanceUnit.MM, -1250, 800, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_5 = new Pose2D(DistanceUnit.MM, -400, 1000, AngleUnit.DEGREES, 0);
//    static final Pose2D TARGET_5_33 = new Pose2D(DistanceUnit.MM, -1250, 900, AngleUnit.DEGREES, 0);
//    static final Pose2D TARGET_5_66 = new Pose2D(DistanceUnit.MM, -400, 1100, AngleUnit.DEGREES, 0);
//    static final Pose2D TARGET_6 = new Pose2D(DistanceUnit.MM, -451,850, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_7 = new Pose2D(DistanceUnit.MM, -193, 780, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_8 = new Pose2D(DistanceUnit.MM, -200, -200, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_9 = new Pose2D(DistanceUnit.MM, -780+50, -200, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_10 = new Pose2D(DistanceUnit.MM, -193, 780, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_11 = new Pose2D(DistanceUnit.MM, -200, -400, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_12 = new Pose2D(DistanceUnit.MM, -780+50, -400, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_13 = new Pose2D(DistanceUnit.MM, -200, 1100, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_14 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_15 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    static final Pose2D TARGET_16 = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
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

        activeArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        activeArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cap.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cap.setDirection(DcMotor.Direction.REVERSE);

        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-15, -5); //these are tuned for the robot
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
                    if (nav.driveTo(odo.getPosition(), TARGET_1, .7, .5)){
                        back_arm_bar();
                        sleep(500);
                        forward_arm_up();
//                        forward_arm_back();
//                        sleep(800);
//                        transfer();
//                        sleep(500);
                        highRung();
                        sleep(600);
                        telemetry.addLine("at position #1!");

                        stateMachine = StateMachine.DRIVE_TO_TARGET_2;
                    }
                    break;
                case DRIVE_TO_TARGET_2:
                    //drive to the second target
                    if (nav.driveTo(odo.getPosition(), TARGET_2, 1, 0)){
//                        wristdown();
//                        sleep(200);
                        grab();
                        sleep(500);
                        forward_arm_back();
//                        forward_arm_up();
                        bottom();
//                        sleep(300);



                        telemetry.addLine("at position #2!");
                        stateMachine = StateMachine.DRIVE_TO_TARGET_3;
                    }
                    break;
                case DRIVE_TO_TARGET_3:
                    if(nav.driveTo(odo.getPosition(), TARGET_3, 1, 0)){
                        telemetry.addLine("at position #3");
                        transfer();

//                        swivelZero();
//                        sleep(500);


                        stateMachine = StateMachine.DRIVE_TO_TARGET_4;
                    }
                    break;
                case DRIVE_TO_TARGET_4:
                    if(nav.driveTo(odo.getPosition(),TARGET_4,1,0)){
                        telemetry.addLine("at position #4");

                        stateMachine = StateMachine.DRIVE_TO_TARGET_5;
                    }
                    break;
                case DRIVE_TO_TARGET_5:
                    if(nav.driveTo(odo.getPosition(),TARGET_5,1,.1)){
                        telemetry.addLine("at position #5!");
                        transfer();
                        forward_arm_up();

                        stateMachine = StateMachine.DRIVE_TO_TARGET_7;
                    }
                    break;
//                case DRIVE_TO_TARGET_5_33:
//                    if(nav.driveTo(odo.getPosition(),TARGET_5_33,1,0)){
//                        telemetry.addLine("at position #5!");
////                        forward_arm_up();
//
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_5_66;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_5_66:
//                    if(nav.driveTo(odo.getPosition(),TARGET_5_66,1,0)){
//                        telemetry.addLine("at position #5!");
//
//
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_6;
//                    }
//                    break;
//                case DRIVE_TO_TARGET_6:
//                    if(nav.driveTo(odo.getPosition(), TARGET_6, 1, .5)){
//                        telemetry.addLine("at position #6");
//
//                        stateMachine = StateMachine.DRIVE_TO_TARGET_7;
//                    }
//                    break;
                case DRIVE_TO_TARGET_7:
                    if(nav.driveTo(odo.getPosition(), TARGET_7, .8, .5)){
                        telemetry.addLine("at position #7");
                        grab();
                        sleep(500);
                        swivelSet();
                        forward_arm_back();
                        sleep(300);
                        transfer();
                        back_arm_bar();
                        highRung();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_8;
                    }
                    break;
                case DRIVE_TO_TARGET_8:
                    if(nav.driveTo(odo.getPosition(), TARGET_8, 1, 0)){
                        telemetry.addLine("at position #8");


                        stateMachine = StateMachine.DRIVE_TO_TARGET_9;
                    }
                    break;
                case DRIVE_TO_TARGET_9:
                    if(nav.driveTo(odo.getPosition(), TARGET_9, .7, 0)){
//                        highRung();
//                        sleep(1000);

                        grab();
                        sleep(400);
                        forward_arm_up();
                        bottom();
//                        sleep(300);
                        transfer();
                        telemetry.addLine("at position #9");
//                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_10;
                    }
                    break;
                case DRIVE_TO_TARGET_10:
                    if(nav.driveTo(odo.getPosition(), TARGET_10, 1, .5)){
                        telemetry.addLine("at position #10");
                        grab();
                        sleep(500);
                        swivelSet();
//                        sleep(100);
                        forward_arm_back();
                        sleep(300);

//                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_11;
                    }
                    break;
                case DRIVE_TO_TARGET_11:
                    if(nav.driveTo(odo.getPosition(), TARGET_11, 1, .5)){
                        telemetry.addLine("at position #11");

                        transfer();
//                        transfer();
//                        back_arm_bar();
                        back_arm_bar();
                        highRung();
                        sleep(600);
                        stateMachine = StateMachine.DRIVE_TO_TARGET_12;
                    }
                    break;
                case DRIVE_TO_TARGET_12:
                    if(nav.driveTo(odo.getPosition(), TARGET_12, 1, 0)){

                        grab();
                        sleep(400);
                        forward_arm_front();
                        bottom();
//                        sleep(300);
                        transfer();
                        telemetry.addLine("at position #12");
//                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_13;
                    }
                    break;
                case DRIVE_TO_TARGET_13:
                    if(nav.driveTo(odo.getPosition(), TARGET_13, 1, 0)){

                        telemetry.addLine("at position #12");
//                        transfer();
                        stateMachine = StateMachine.DRIVE_TO_TARGET_14;
                    }
                    break;
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
    public void grab(){
        backGrab.setPosition(.8);
        grab.setPosition(0.44);
    }
    public void transfer(){
        backGrab.setPosition(.5);
        sleep(150);
        grab.setPosition(1);
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
    public void forward_arm_front(){
        wrist.setPosition(.63);

        wrist1.setPosition(.91);
        wrist2.setPosition(0.08);
        swivel.setPosition(.68);
        arm1.setPosition(0.46);
        arm2.setPosition(.53);

    }
    public void forward_arm_back(){
        wrist.setPosition(.78);

        wrist1.setPosition(0.21);
        wrist2.setPosition(.78);
        swivel.setPosition(.65);
        sleep(200);
        arm1.setPosition(.85);
        arm2.setPosition(0.14);
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
    public void wristdown(){
        wrist.setPosition(.6);
    }
    public void back_arm_bar(){
        wrist.setPosition(.3);
        arm1.setPosition(0.33);
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
        cap.setTargetPosition(-3150);
        cap2.setTargetPosition(-3150);
        cap.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        cap2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void highRung() {
        cap.setPower(1);
        cap2.setPower(1);
        cap.setTargetPosition(-670);
        cap2.setTargetPosition(-670);
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
        cap.setPower(.6);
        cap2.setPower(.6);

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
}