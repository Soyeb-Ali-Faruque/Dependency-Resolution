package com.s5.modulink.repository;

import com.s5.modulink.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {

    @Query(value = "select module_id from modules",nativeQuery = true)
    List<Long> getAllModulesId();
}
