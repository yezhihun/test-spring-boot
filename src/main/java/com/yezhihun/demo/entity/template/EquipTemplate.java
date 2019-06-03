package com.yezhihun.demo.entity.template;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.EquipPotenial;

import javax.persistence.*;

/**
 * 装备模板
 * Created by tianye on 2019/5/27.
 */
@Entity
@Table(name = "equip_template")
public class EquipTemplate extends Biology{

    /**
     * 装备潜力值
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "equip_potenial", columnDefinition = "varchar(100) COMMENT '装备品级'")
    private EquipPotenial equipPotenial;

    public EquipPotenial getEquipPotenial() {
        return equipPotenial;
    }

    public void setEquipPotenial(EquipPotenial equipPotenial) {
        this.equipPotenial = equipPotenial;
    }
}
