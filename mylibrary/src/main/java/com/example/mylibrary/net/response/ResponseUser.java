package com.example.mylibrary.net.response;


import com.example.mylibrary.bean.net.BaseResponse;

import java.util.ArrayList;

public class ResponseUser extends BaseResponse {

    /**
     "data":{
     "event":{
     "avatarUrl":"http://192.168.10.106:9006/defaultAvatar/studentDefault.png",
     "currentDate":1535683941141,
     "enterTime":"",
     "firstLanding":false,
     "fullname":"13111591939",
     "gender":1,
     "groupId":"",
     "groups":[{"groupName":"四年研修级一班","id":"a9c20df25d8511e891a0fa163e29292b","url":"5bd51e42477c4f578ced4b3688daf024"}],
     "isValid":0,
     "schoolId":"0",
     "schoolLogoUrl":"",
     "schoolName":"",
     "subject":"",
     "token":"d90623ca96e811e8a6d254bef74519ee_3b97b6e3176b422e817a6aeeb002da8e",
     "userId":"d90623ca96e811e8a6d254bef74519ee",
     "userManagement":5,
     "username":"13111591939",
     "webAuthorities":[
     "ROLE_Student"
     ],
     "xd":0
     },
     "isLogin":-2
     }
     */

    public DATA data;

    public static class DATA{
        public EVENT event;
        public String isLogin;
    }

    public static class EVENT{
        public String avatarUrl;
        public String avatarBoxUrl;
        public long currentDate;
        public String enterTime;
        public boolean firstLanding;
        public String fullname;
        public int gender;
        public String groupId;
        public ArrayList<GROUP> groups;
        public int isValid;
        public String schoolId;
        public String schoolLogoUrl;
        public String schoolName;
        public String subject;
        public String token;
        public String userId;
        public int userManagement;
        public String username;
        public ArrayList<String> webAuthorities;
        public int xd;
    }

    public static class GROUP{
        public String groupName;
        public String id;
        public String url;
    }

}
