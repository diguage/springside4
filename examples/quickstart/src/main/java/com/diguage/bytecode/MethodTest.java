package com.diguage.bytecode;

/**
 * Created by diguage on 16/8/24.
 */
public class MethodTest {
    public void start() {
        long __startTime = System.currentTimeMillis();
    }

    public void clock() {
        long __startTime = System.currentTimeMillis();
        long __endTime = System.currentTimeMillis();
    }

    public long returnVar() {
        long __startTime = System.currentTimeMillis();
        long __endTime = System.currentTimeMillis();
        return __endTime - __startTime;
    }


    public long multiVar() {
        long __startTime = System.currentTimeMillis();
        String name = "var1";
        int age = 29;
        boolean isMale = true;
        long __endTime = System.currentTimeMillis();
        return __endTime - __startTime;
    }
}
