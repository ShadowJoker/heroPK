/**
 * DefIceShield.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.symptoms.SymptomFrozen;

/**
 * ±ù¶Ü¼¼ÄÜ
 * 
 * @author Zhang Peng 2014-6-22
 */
public class DefIceShield extends DefenceSkill {

    public DefIceShield(Hero activeHero, Hero targetHero) {
        super("±ù¶Ü", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        targetHero.setDamageOutput(0);
        SymptomFrozen frozen = new SymptomFrozen(5000, activeHero, targetHero);
        frozen.run();
    }

}
