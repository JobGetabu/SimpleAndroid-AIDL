package com.sendy.calcservice;

import android.os.RemoteException;

public class Calculations extends ICalc.Stub{

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divide(int a, int b) throws RemoteException {
        return a / b;
    }
}
