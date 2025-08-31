package com.s5.modulink.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dependencies")
public class Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dependencyId;


    private long moduleId;

    private long dependedModuleId;

    public long getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(long dependencyId) {
        this.dependencyId = dependencyId;
    }

    public long getDependedModuleId() {
        return dependedModuleId;
    }

    public void setDependedModuleId(long dependedModuleId) {
        this.dependedModuleId = dependedModuleId;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
}
