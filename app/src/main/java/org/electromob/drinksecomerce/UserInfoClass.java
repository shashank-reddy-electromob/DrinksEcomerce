package org.electromob.drinksecomerce;

public class UserInfoClass {

    public String firebase_name,firebase_email,firebase_phone_number,firebase_password;

    public UserInfoClass() {
    }

    public UserInfoClass(String firebase_name, String firebase_email, String firebase_phone_number, String firebase_password) {
        this.firebase_name = firebase_name;
        this.firebase_email = firebase_email;
        this.firebase_phone_number = firebase_phone_number;
        this.firebase_password = firebase_password;
    }

    public String getFirebase_name() {
        return firebase_name;
    }

    public void setFirebase_name(String firebase_name) {
        this.firebase_name = firebase_name;
    }

    public String getFirebase_email() {
        return firebase_email;
    }

    public void setFirebase_email(String firebase_email) {
        this.firebase_email = firebase_email;
    }

    public String getFirebase_phone_number() {
        return firebase_phone_number;
    }

    public void setFirebase_phone_number(String firebase_phone_number) {
        this.firebase_phone_number = firebase_phone_number;
    }

    public String getFirebase_password() {
        return firebase_password;
    }

    public void setFirebase_password(String firebase_password) {
        this.firebase_password = firebase_password;
    }
}
