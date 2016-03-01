/**
 * NullDefenceSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * �շ������ܣ����ڲ���
 * @author Zhang Peng 2014-6-9
 */
public class NullDefenceSkill extends DefenceSkill {

    public NullDefenceSkill(Hero activeHero, Hero targetHero) {
        super("�շ�������", 0.5, activeHero, targetHero);
    }

    @Override
    public void excute() {
        // do nothing
    }

}
