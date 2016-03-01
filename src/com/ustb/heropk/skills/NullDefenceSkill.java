/**
 * NullDefenceSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 空防御技能，用于测试
 * @author Zhang Peng 2014-6-9
 */
public class NullDefenceSkill extends DefenceSkill {

    public NullDefenceSkill(Hero activeHero, Hero targetHero) {
        super("空防御技能", 0.5, activeHero, targetHero);
    }

    @Override
    public void excute() {
        // do nothing
    }

}
