/**
 * SymptomFrozen.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.utils.GameUtils;

/**
 * 冰冻症状，将敌人冻结使其不能攻击
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomFrozen extends Symptom {

    public SymptomFrozen(long lastTime, Hero activeHero, Hero targetHero) {
        super("冰冻", lastTime, activeHero, targetHero);
    }

    @Override
    public void activeEffectByReset() {
        targetHero.getTimerTask().cancel();
        targetHero.getTimer().cancel();
    }

    @Override
    public void removeEffect() {
        Timer timer = new Timer();
        TimerTask task = GameUtils.getNewTask(targetHero);
        targetHero.setTimer(timer);
        timer.schedule(task, 0, targetHero.getAttackRate());
    }

}
