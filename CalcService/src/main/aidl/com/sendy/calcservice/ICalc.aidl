// ICalc.aidl
package com.sendy.calcservice;

// Declare any non-default types here with import statements

interface ICalc {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int a, int b);
    int subtract(int a, int b);
    double multiply(int a ,int b);
    double divide(int a , int b);
}
