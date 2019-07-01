package com.voodoo.ulca.entity;

public class Hero {
    private String name;//名字
    private String abilities;//技能
    private String[] extra_abilities;//额外技能

    private int armor;//防御
    private double attackAnimationPoint;
    private int attackRange;//攻击范围
    private double attackRate;//攻击速度
    private int[] damageMin;//最小伤害
    private int[] damageMax;//最大伤害

    private String displayName;//显示名称
    private String dota_unit_name;//单位名称
    private int draftTier;//级别
    private int goldCost;//价格
    private int[] health;//血量
    private int healthRegen;//血量回复
    private int id;
    private String keywords;//队伍
    private int magicResist;//魔法回复
    private int maxmana;//最大魔法
    private int movespeed;//移动速度
}
