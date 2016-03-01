/**
 * OffGodArmor.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.symptoms.SymptomDizziness;

/**
 * 重击技能
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffHeavyHit extends OffenceSkill {

    SymptomDizziness dizz = new SymptomDizziness(8000, activeHero, targetHero);;

    public OffHeavyHit(Hero activeHero, Hero targetHero) {
        super("重击", 0.25, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage());
        dizz.run();
    }

}
