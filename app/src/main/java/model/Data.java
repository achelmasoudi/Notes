package model;

public class Data {

    private int id;
    private String title;
    private String description;
    private String timeStamp;

    public Data() {
    }
    public Data(String pTitle , String pDescription) {
        this.title = pTitle;
        this.description = pDescription;
    }
    public Data(int pId , String pTitle , String pDescription) {
        this.id = pId;
        this.title = pTitle;
        this.description = pDescription;
    }
    public Data(int pId , String pTitle , String pDescription , String pTimeStamp) {
        this.id = pId;
        this.title = pTitle;
        this.description = pDescription;
        this.timeStamp = pTimeStamp;
    }

    //Getters and Setters

    public void setId(int pId) {
        this.id = pId;
    }
    public int getId() {
        return this.id;
    }

    public void setTitle(String pTitle) {
        this.title = pTitle;
    }
    public String getTitle() {
        return this.title;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }
    public String getDescription() {
        return this.description;
    }

    public void setTimeStamp(String pTimeStamp) {
        this.timeStamp = pTimeStamp;
    }
    public String getTimeStamp() {
        return this.timeStamp;
    }





}
