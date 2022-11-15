package org.firstinspires.ftc.Team19567.opmodes.Autonomous;

import static org.firstinspires.ftc.Team19567.util.UtilConstants.tagFirstId;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.tagSecondId;
import static org.firstinspires.ftc.Team19567.util.UtilConstants.tagThirdId;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Team19567.util.ParkingLocation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public class BlueTerminalSubstation extends OpMode {
    OpenCvCamera camera;
    AprilTagPipeline aprilTagPipeline;
    ParkingLocation location = ParkingLocation.None;
    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagPipeline = new AprilTagPipeline(telemetry);
        camera.setPipeline(aprilTagPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
    }

    @Override
    public void loop() {
        ArrayList<AprilTagDetection> curentDetections = aprilTagPipeline.getLatestDetections();
        if(curentDetections.size() == 1){
            for (AprilTagDetection detection : curentDetections){
                if (detection.id == tagFirstId)
                {
                    location = ParkingLocation.First;
                }
                else if(detection.id == tagSecondId){
                    location = ParkingLocation.Second;
                }
                else if(detection.id == tagThirdId){
                    location = ParkingLocation.Third;
                }
            }
        }
    }
}
