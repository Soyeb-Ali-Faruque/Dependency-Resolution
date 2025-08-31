package com.s5.modulink.repository;

import com.s5.modulink.model.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DependencyRepository extends JpaRepository<Dependency,Long> {

    @Query(value = "select module_id from dependencies where depended_module_id = :id",nativeQuery = true)
    List<Long> getList(@Param("id") long moduleId);
}
