/**
 * SymptomTest.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Date;

import com.ustb.heropk.heros.Hero;

/**
 * Ö¢×´ÉúĞ§²âÊÔÀà
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomTest extends Symptom {

    private static Date date = new Date();

    public SymptomTest(long lastTime, Hero activeHero, Hero targetHero) {
        super("Ö¢×´²âÊÔ", lastTime, activeHero, targetHero);
    }

    @Override
    public void activeEffectByReset() {
        System.out.println("Ö¢×´¿ªÊ¼" + (new Date().getTime() - date.getTime()));
    }

    @Override
    public void removeEffect() {
        System.out.println("Ö¢×´½áÊø" + (new Date().getTime() - date.getTime()));
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        SymptomTest.date = date;
    }

}
