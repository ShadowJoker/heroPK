/**
 * DefGodArmor.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * ��׼���
 * 
 * @author Zhang Peng 2014-6-22
 */
public class DefGodArmor extends DefenceSkill {

    public DefGodArmor(Hero activeHero, Hero targetHero) {
        super("���", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        targetHero.setDamageOutput(targetHero.getDamageOutput() / 2);
    }

}