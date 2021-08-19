package com.example.mylibrary.util.platform;

public class PlatFormAdapter {

    public static void adapter(PlatForm... platForms){
        for(PlatForm platForm : platForms){
            platForm.adapter();
        }
    }
}
