public class Cell {
    private String oem;
    private String model;
    private int launchAnnounced;
    private String launchStatus;
    private String bodyDimensions;
    private float bodyWeight;
    private String bodySim;
    private String displayType;
    private float displaySize;
    private String displayResolution;
    private String featuresSensors;
    private String platformOs;

    // Getters and setters
    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLaunchAnnounced() {
        return launchAnnounced;
    }

    public void setLaunchAnnounced(int launchAnnounced) {
        this.launchAnnounced = launchAnnounced;
    }

    public String getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(String launchStatus) {
        this.launchStatus = launchStatus;
    }

    public String getBodyDimensions() {
        return bodyDimensions;
    }

    public void setBodyDimensions(String bodyDimensions) {
        this.bodyDimensions = bodyDimensions;
    }

    public float getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public String getBodySim() {
        return bodySim;
    }

    public void setBodySim(String bodySim) {
        this.bodySim = bodySim;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(float displaySize) {
        this.displaySize = displaySize;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public String getFeaturesSensors() {
        return featuresSensors;
    }

    public void setFeaturesSensors(String featuresSensors) {
        this.featuresSensors = featuresSensors;
    }

    public String getPlatformOs() {
        return platformOs;
    }
    public void setPlatformOs(String platformOs) {
        this.platformOs = platformOs;
    }


    /**
     * Returns a string representation of the phone
     * @return A string representation of the phone
     */

    @Override
    public String toString() {
        return "oem:" + oem + ", model:" + model + ", launch_announced: " + launchAnnounced + ", launch_status: " +
                launchStatus + ", body_dimensions: " + bodyDimensions + ", body_weight: " + bodyWeight + ", body_sim: " +
                bodySim + ", display_type: " + displayType + ",display_size:" + displaySize + ", display_resolution:" +
                displayResolution + ", features_sensors:" + featuresSensors + ", platform_os:" + platformOs;
    }
}