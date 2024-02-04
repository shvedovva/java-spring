package org.example;

public class Camera {
    private CameraRoll cameraRoll;

    public Camera(){
        this.cameraRoll = new CameraRoll();
    }

    public void doPhoto(){
        System.out.println("Click1!");
        cameraRoll.processing();
    }

}
