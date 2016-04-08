package com.mailab.azharul.contact;


public class ContactModel {

    private String name;
    private String phoneNO;
    private int id;
    //private int imageID;

    public ContactModel(String name, String phoneNO, int id) {
        this.name = name;
        this.phoneNO = phoneNO;
        this.id = id;
    }

    public ContactModel(String name, String phoneNO) {
        this.name = name;
        this.phoneNO = phoneNO;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public int getId() {
        return id;
    }


}
