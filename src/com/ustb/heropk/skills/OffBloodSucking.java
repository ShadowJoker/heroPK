/**
 * OffBloodSucking.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 吸血技能
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffBloodSucking extends OffenceSkill {

    public OffBloodSucking(Hero activeHero, Hero targetHero) {
        super("吸血", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage());
        int damage = activeHero.getDamageOutput();
        if ((activeHero.getHealthLimit() - activeHero.getHealth()) < damage) {
            // 如果输出伤害大于当前损失的血量，将血补满，防止血量溢出
            activeHero.setHealth(activeHero.getHealthLimit());
        } else {
            // 回复与伤害量等量的血,相当于把对方伤害输出减少
            targetHero.setDamageOutput(targetHero.getDamageOutput() - damage);
        }
    }

}
