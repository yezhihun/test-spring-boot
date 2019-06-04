package com.yezhihun.demo.entity.template;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.EquipCategory;
import com.yezhihun.demo.enums.EquipPotenial;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 装备模板
 * Created by tianye on 2019/5/27.
 */
@Entity
@Table(name = "equip_template")
@Proxy(lazy = false)
public class EquipTemplate extends Biology{

    /**
     * 装备潜力值
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "equip_potenial", columnDefinition = "varchar(100) COMMENT '装备品级'")
    private EquipPotenial equipPotenial;

    @Enumerated(EnumType.STRING)
    @Column(name = "equip_category", columnDefinition = "varchar(100) COMMENT '部位'")
    private EquipCategory equipCategory;

    public EquipPotenial getEquipPotenial() {
        return equipPotenial;
    }

    public void setEquipPotenial(EquipPotenial equipPotenial) {
        this.equipPotenial = equipPotenial;
    }

    public EquipCategory getEquipCategory() {
        return equipCategory;
    }

    public void setEquipCategory(EquipCategory equipCategory) {
        this.equipCategory = equipCategory;
    }
}
