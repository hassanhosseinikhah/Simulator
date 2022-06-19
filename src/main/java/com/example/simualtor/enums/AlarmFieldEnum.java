package com.example.simualtor.enums;


import java.util.HashMap;
import java.util.Map;

public enum AlarmFieldEnum {

    ALARM_RESTORE_TIME("alarmRestoreTime"),
    ALARM_OPERATOR("alarmOperator"),
    ALARM_SERVICE_AFFECT_FLAG("alarmServiceAffectFlag"),
    ALARM_RESTORE("alarmRestore"),
    ALARM_ACK_TIME("alarmAckTime"),
    ALARM_LEVEL("alarmLevel"),
    ALARM_SPECIFIC_PROBLEMS("alarmSpecificProblems"),
    ALARM_ADDITIONAL_INFO("alarmAdditionalInfo"),
    ALARM_TYPE("alarmType"),
    ALARM_MO_NAME("alarmMOName"),
    ALARM_PRODUCT_ID("alarmProductID"),
    ALARM_NE_TYPE("alarmNEType"),
    ALARM_NE_DEV_ID("alarmNEDevID"),
    ALARM_EXTEND_INFO("alarmExtendInfo"),
    ALARM_CATEGORY("alarmCategory"),
    ALARM_PROPOSED_REPAIR_ACTIONS("alarmProposedRepairActions"),
    ALARM_CLEAR_CATEGORY("alarmClearCategory"),
    ALARM_PROBABLE_CAUSE("alarmProbableCause"),
    ALARM_CLEAR_TYPE("alarmClearType"),
    ALARM_CLEAR_OPERATOR("alarmClearOperator"),
    ALARM_OBJECT_INSTANCE_TYPE("alarmObjectInstanceType"),
    ALARM_DEV_CSN("alarmDevCsn"),
    ALARM_ID("alarmID"),
    ALARM_CONFIRM("alarmConfirm"),
    ALARM_OCCUR_TIME("AlarmOccurTime");


    private static final Map<String, AlarmFieldEnum> nameMap = new HashMap<>();

    static {
        for (final AlarmFieldEnum enumValue : AlarmFieldEnum.values()) {
            nameMap.put(enumValue.value, enumValue);
        }
    }

    private final String value;

    AlarmFieldEnum(String value) {
        this.value = value;
    }

    public static AlarmFieldEnum getEnum(String name) {
        return nameMap.get(name);
    }

    public String getValue() {
        return value;
    }

}
