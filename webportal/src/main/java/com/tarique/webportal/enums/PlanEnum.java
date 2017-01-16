package com.tarique.webportal.enums;

/**
 * Created by Mehnuma on 1/15/2017.
 */
public enum PlanEnum {

    BASIC_PLAN(1, "Basic"), PROD_PLAN(2, "Prod");

    private final int id;
    private final String planName;

    PlanEnum(int id, String planName) {
        this.id = id;
        this.planName = planName;
    }
    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }

}
