package com.s5.modulink.dto;

public class ModuleOrder {
    private int orderId;
    private long moduleId;

    public ModuleOrder(int orderId,long moduleId){
        this.orderId = orderId;
        this.moduleId = moduleId;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
}
