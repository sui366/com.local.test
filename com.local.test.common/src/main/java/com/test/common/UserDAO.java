package com.test.common;


public class UserDAO {
 
    public void add(Object o) {
        System.out.println("UserDAO -> Add: " + o.toString());
    }
 
    public void get(Object o) {
        System.out.println("UserDAO -> Get: " + o.toString());
    }
}
