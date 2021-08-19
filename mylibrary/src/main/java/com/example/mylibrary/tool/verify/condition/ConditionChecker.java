package com.example.mylibrary.tool.verify.condition;

public class ConditionChecker implements IChecker{

    @Override
    public boolean checkPassword(String password) {
        String passwordReg = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,50})$";
        return password.matches(passwordReg);
    }
}
