package com.ptp.phamtanphat.multiplethread;

/**
 * Created by KhoaPhamPC on 24/10/2017.
 */

public class DataObject {
    private int A;
    private int B;

    public DataObject() {

    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int Tinhtong(){
       return this.A + this.B;
    }
}
