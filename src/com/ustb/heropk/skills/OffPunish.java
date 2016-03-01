/**
 * OffPunish.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.symptoms.SymptomInterrupt;

/**
 * �ͽ似��
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffPunish extends OffenceSkill {

    public OffPunish(Hero activeHero, Hero targetHero) {
        super("�ͽ�", 0.4, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage());
        SymptomInterrupt interrupt = new SymptomInterrupt(activeHero, targetHero);
        interrupt.run();
    }

}
