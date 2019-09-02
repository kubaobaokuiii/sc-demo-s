package com.bocloud.boms.framework.flight.service;


import com.bocloud.boms.framework.flight.model.FightManagement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "hang-ban-service")
public interface FightManagementService {

    /**
     * 根据ID获得飞机管理信息
     * @param id
     * @return
     */
    @GetMapping("/fight/test/{id}")
    FightManagement fightId(@PathVariable("id") Integer id);

    /**
     * 获得所有的飞机管理信息
     * @return
     */
    @GetMapping("/fight")
    List<FightManagement> findAllFight();

    /**
     * 根据ID删除飞机管理信息
     * @param id
     */
    @DeleteMapping("/fight/{id}")
    void FightDeleteById(@PathVariable("id") Integer id);

    /**
     * 更新用飞机管理信息
     * @param
     */
    @PutMapping("/updateFight")
    void FightUpdate(@RequestBody FightManagement fightManagement);

    /**
     * 添加飞机管理信息
     * @param fightManagement
     */
    @RequestMapping(value = "/fight/add", method = RequestMethod.POST)
    FightManagement insertFight(@RequestBody FightManagement fightManagement);

    /**
     * 根据用户查询所对应的机票信息
     */
//    @GetMapping("/fight/user/{id}")
//    List<FightManagement> userToFight(@PathVariable("id") Integer id);

}
