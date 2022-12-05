package org.firstinspires.ftc.simplerobotcode.drive.opmodes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.simplerobotcode.drive.util.ParkingLocation;
import org.openftc.easyopencv.OpenCvCamera;

public class BlueTerminalSubstation extends OpMode {
    OpenCvCamera camera;
    AprilTagPipeline aprilTagPipeline;
    ParkingLocation location = ParkingLocation.None;

    @Override
    public void init() {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        aprilTagPipeline = new AprilTagPipeline(telemetry);
//        camera.setPipeline(aprilTagPipeline);
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
//        {
//            @Override
//            public void onOpened()
//            {
//                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode)
//            {
//
//            }
//        });
    }

    @Override
    public void loop() {
//        ArrayList<AprilTagDetection> curentDetections = aprilTagPipeline.getLatestDetections();
//        if(curentDetections.size() == 1){
//            for (AprilTagDetection detection : curentDetections){
//                if (detection.id == tagFirstId)
//                {
//                    location = ParkingLocation.First;
//                }
//                else if(detection.id == tagSecondId){
//                    location = ParkingLocation.Second;
//                }
//                else if(detection.id == tagThirdId){
//                    location = ParkingLocation.Third;
//                }
//            }
//        }
//    }
    }
}
