/**
 * SymptomTestLauncer.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Date;

import com.ustb.heropk.heros.BMHero;
import com.ustb.heropk.heros.DHHero;

/**
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomTestLauncer {

    /**
     * @param args
     * @author Zhang Peng 2014-6-22
     */
    public static void main(String[] args) {
        BMHero bm = new BMHero();
        DHHero dh = new DHHero();
        SymptomTest test = new SymptomTest(3000, bm, dh);
        test.run();
        System.out.println("主线程停顿开始" + (new Date().getTime()-SymptomTest.getDate().getTime()));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程停顿结束，再次施放症状" + (new Date().getTime()-SymptomTest.getDate().getTime()));
        test.run();
    }
}
