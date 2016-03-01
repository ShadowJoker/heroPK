/**
 * OffenceSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 攻击技能抽象父类
 * 
 * @author Zhang Peng 2014-6-9
 */
public abstract class OffenceSkill extends AbstractSkill {
    public OffenceSkill(String skillname, double activeRate, Hero activeHero, Hero targetHero) {
        super(skillname, activeRate, activeHero, targetHero);
    }
}
