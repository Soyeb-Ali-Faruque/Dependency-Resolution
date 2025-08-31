package com.s5.modulink.controller;

import com.s5.modulink.dto.ModuleOrder;
import com.s5.modulink.service.DependencyResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ModuLinkController {
    @Autowired
    private DependencyResolutionService service;
    @GetMapping("/run")
    public ResponseEntity<List<ModuleOrder>> resolveDependencies(){
        List<ModuleOrder> response = service.generateValidOrder();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
