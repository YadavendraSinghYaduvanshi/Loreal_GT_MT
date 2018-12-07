package com.cpm.GetterSetter;

/**
 * Created by deepakp on 20-03-2018.
 */

public class ImageStatusGetterSetter {
    @Override
    public String toString() {
        return imageName + " : " + status + " = " + serverResponse;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }

    String imageName;
    String status;
    String serverResponse;
}
