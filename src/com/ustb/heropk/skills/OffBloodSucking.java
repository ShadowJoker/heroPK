/**
 * OffBloodSucking.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * ��Ѫ����
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffBloodSucking extends OffenceSkill {

    public OffBloodSucking(Hero activeHero, Hero targetHero) {
        super("��Ѫ", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage());
        int damage = activeHero.getDamageOutput();
        if ((activeHero.getHealthLimit() - activeHero.getHealth()) < damage) {
            // �������˺����ڵ�ǰ��ʧ��Ѫ������Ѫ��������ֹѪ�����
            activeHero.setHealth(activeHero.getHealthLimit());
        } else {
            // �ظ����˺���������Ѫ,�൱�ڰѶԷ��˺��������
            targetHero.setDamageOutput(targetHero.getDamageOutput() - damage);
        }
    }

}
