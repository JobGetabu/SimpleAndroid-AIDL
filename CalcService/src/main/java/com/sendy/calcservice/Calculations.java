package com.sendy.calcservice;

import android.os.RemoteException;

public class Calculations extends ICalc.Stub{

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
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
