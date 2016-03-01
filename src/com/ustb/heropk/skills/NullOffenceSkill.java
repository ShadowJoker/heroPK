/**
 * NullOffenceSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 空攻击技能，用于测试
 * 
 * @author Zhang Peng 2014-6-9
 */
public class NullOffenceSkill extends OffenceSkill {

    public NullOffenceSkill(Hero activeHero, Hero targetHero) {
        super("空攻击技能", 0.5, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage());
    }

}
