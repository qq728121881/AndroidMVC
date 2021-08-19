package com.example.mylibrary.tool.verify.condition;

public interface IChecker {

    //验证密码格式是否符合要求
    boolean checkPassword(String password);

    //TODO 可以往后加一系列的验证方法
}
