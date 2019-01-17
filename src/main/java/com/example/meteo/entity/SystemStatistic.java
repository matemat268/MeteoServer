package com.example.meteo.entity;

public class SystemStatistic {

    private int logInToday;
    private int logInMonth;
    private int newUsersToday;
    private int newUsersMonth;
    private User newestUser;

    public SystemStatistic() {
    }

    public SystemStatistic(int logInToday, int logInMonth, int newUsersToday, int newUsersMonth, User newestUser) {
        this.logInToday = logInToday;
        this.logInMonth = logInMonth;
        this.newUsersToday = newUsersToday;
        this.newUsersMonth = newUsersMonth;
        this.newestUser = newestUser;
    }

    public int getLogInToday() {
        return logInToday;
    }

    public void setLogInToday(int logInToday) {
        this.logInToday = logInToday;
    }

    public int getLogInMonth() {
        return logInMonth;
    }

    public void setLogInMonth(int logInMonth) {
        this.logInMonth = logInMonth;
    }

    public int getNewUsersToday() {
        return newUsersToday;
    }

    public void setNewUsersToday(int newUsersToday) {
        this.newUsersToday = newUsersToday;
    }

    public int getNewUsersMonth() {
        return newUsersMonth;
    }

    public void setNewUsersMonth(int newUsersMonth) {
        this.newUsersMonth = newUsersMonth;
    }

    public User getNewestUser() {
        return newestUser;
    }

    public void setNewestUser(User newestUser) {
        this.newestUser = newestUser;
    }
}
