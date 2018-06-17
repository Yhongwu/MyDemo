package com.howard.demo.multithreading.exercise06.parallelassemblyline;

public class Msg {
    public double i;
    public double j;
    public String orgStr = null;

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public String getOrgStr() {
        return orgStr;
    }

    public void setOrgStr(String orgStr) {
        this.orgStr = orgStr;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "i=" + i +
                ", j=" + j +
                ", orgStr='" + orgStr + '\'' +
                '}';
    }
}
