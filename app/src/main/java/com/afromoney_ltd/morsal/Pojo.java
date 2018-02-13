package com.afromoney_ltd.morsal;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jaafaralmahy on 2/7/18.
 */

public class Pojo {

    private int systemTraceNum;
    private String terminalId;
    private String tranDateTime;
    private String workingKey;
    private String expDate;
    private String PAN;
    private String PIN;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOriginalSystemTraceNum() {
        return originalSystemTraceNum;
    }

    public void setOriginalSystemTraceNum(String originalSystemTraceNum) {
        this.originalSystemTraceNum = originalSystemTraceNum;
    }

    private String originalSystemTraceNum;

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getWorkingKey() {
        return workingKey;
    }

    public void setWorkingKey(String workingKey) {
        this.workingKey = workingKey;
    }

    public int getSystemTraceNum() {
        return systemTraceNum;
    }

    public void setSystemTraceNum(int systemTraceNum) {
        this.systemTraceNum = systemTraceNum;
    }

    public String getTerminalId() {
        terminalId = "22002204";
        return terminalId;
    }

    public String getTranDateTime() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        tranDateTime = df.format(Calendar.getInstance().getTime());
        return tranDateTime;
    }



}
