/**
 * TimerDemo.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public class TimerDemo {

    private Timer timerA;
    private Timer timerB;
    private TimerTask taskA;
    private TimerTask taskB;

    public void init() {
        timerA = new Timer();
        timerB = new Timer();
        taskA = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task A");
            }
        };
        taskB = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task B");
            }
        };
    }

    public void start() {
        timerA.schedule(taskA, 1000, 1000);
        timerB.schedule(taskB, 2000, 2000);
    }

    public static void main(String[] args) {
        TimerDemo demo = new TimerDemo();
        demo.init();
        demo.start();
    }

}
