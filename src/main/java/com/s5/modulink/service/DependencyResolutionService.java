package com.s5.modulink.service;

import com.s5.modulink.dto.ModuleOrder;
import com.s5.modulink.repository.DependencyRepository;
import com.s5.modulink.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class DependencyResolutionService {
    @Autowired
    private DependencyRepository dependencyRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    private  Map<Long, List<Long>> generateAdjacencyList(){
        Map<Long,List<Long>> map = new HashMap<>();
        List<Long> moduleList = moduleRepository.getAllModulesId();
        for(int i = 0;i<moduleList.size();i++){
            long currentId = moduleList.get(i);
            map.put(currentId,dependencyRepository.getList(currentId));
        }
        return map;
    }

    private boolean checkCycle(Long id,Map<Long,List<Long>> map,Map<Long,Boolean> visited,Map<Long,Boolean> stack){
        if(stack.get(id)) return true;
        if(visited.get(id)) return false;
        visited.put(id,true);
        stack.put(id,true);
        List<Long> modulesDependencies = map.get(id);
        for(Long currentId : modulesDependencies){
            boolean value = checkCycle(currentId,map,visited,stack);
            if (value) return true; // cycle detected
        }
        stack.put(id,false);
        return false;
    }

    private void topoSort(long id,Map<Long,List<Long>> map,Map<Long,Boolean> visited,Stack<Long> orderStack){
        if(visited.get(id)) return;
        visited.put(id, true);
        List<Long> modulesDependencies = map.get(id);
        for (Long currentId : modulesDependencies){
            topoSort(currentId,map,visited,orderStack);
        }
        orderStack.push(id);
    }

    public List<ModuleOrder> generateValidOrder(){
        Map<Long,List<Long>> map = this.generateAdjacencyList();
        Map<Long,Boolean> visited = new HashMap<>();

        //checking cycle
        Map<Long,Boolean> stack = new HashMap<>();
        for(Long id : map.keySet()) {
            visited.put(id,false);
            stack.put(id,false);
        }
        for(Long id : map.keySet()){
            if(!visited.get(id)){
                if(checkCycle(id,map,visited,stack)) return new ArrayList<>();
            }
        }

        //Resolving dependencies
        Stack<Long> orderStack = new Stack<>();
        for(Long id : map.keySet()) visited.put(id,false);
        //resolve dependency
        for(Long id : map.keySet()){
            if(!visited.get(id)) topoSort(id,map,visited,orderStack);
        }

        int orderId = 1;
        List<ModuleOrder> order =  new ArrayList<>();
        while(!orderStack.isEmpty()) order.add(new ModuleOrder(orderId++,orderStack.pop()));
        return order;

    }
}
