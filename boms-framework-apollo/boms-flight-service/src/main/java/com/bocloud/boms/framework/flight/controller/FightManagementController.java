package com.bocloud.boms.framework.flight.controller;

import cn.hutool.core.collection.CollUtil;
import com.bocloud.boms.framework.flight.dto.FightManagementDto;
import com.bocloud.boms.framework.flight.feign.OrderServiceFeign;
import com.bocloud.boms.framework.flight.model.FightManagement;
import com.bocloud.boms.framework.flight.service.FightManagementService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hangBan")
public class FightManagementController {

    @Autowired
    FightManagementService fightManagementService;

    @Autowired
    OrderServiceFeign orderServiceFeign;

//    /**
//     * 查询所有飞机管理信息
//     */
//    @RequestMapping("/allFight")
//    public List<FightManagement> listUsers(Model model) {
//
//        List<FightManagement> fightList =  fightManagementService.findAllFight();
//
//        model.addAttribute("fightList",fightList);
//
//        return fightList;
//    }

    /**
     * 查询所有飞机管理信息
     */
    @RequestMapping("/allFight")
    public List<FightManagementDto> listUsers(Model model) {

        List<FightManagement> list = fightManagementService.findAllFight();
        List<FightManagementDto> fightManagementDtos = CollUtil.newArrayList();
        if (!CollUtil.isEmpty(list)) {
            list.forEach(flightModel -> {
                FightManagementDto fightManagementDto = FightManagementDto.copy(flightModel);
                //调用订单服务测试
                try {
                    String user = orderServiceFeign.getUserCountByFightId((int) flightModel.getFightId());
                    fightManagementDto.setUser(user);
                    fightManagementDtos.add(fightManagementDto);
                    model.addAttribute("fightManagement", fightManagementDtos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return fightManagementDtos;
    }


    /**
     * 根据id查询飞机管理
     */

    @RequestMapping("/allFight/{id}")
    public FightManagement getUserById(@PathVariable("id") Integer id, Model model) {

        FightManagement fightMana = fightManagementService.fightId(id);

        model.addAttribute("findFightById", fightMana);

        return fightMana;
    }

    /**
     * 增加飞机管理信息
     */
    @RequestMapping(value = "/addFight", method = RequestMethod.POST)
    public FightManagement insertUser(@RequestBody FightManagement fightManagement) {
        FightManagement fightId = fightManagementService.insertFight(fightManagement);
        return fightId;
    }

    /**
     * 修改飞机管理信息
     */
    @RequestMapping("/updateFight")
    public void updateFight(@RequestBody FightManagement fightManagement) {

        fightManagementService.FightUpdate(fightManagement);
    }

    /**
     * 删除飞机管理信息
     */
    @RequestMapping("/deleteFight/{id}")
    public void FightDeleteById(@PathVariable("id") Integer id) {

        fightManagementService.FightDeleteById(id);
    }

    /**
     * 根据用户查询所对应的机票信息
     */

//    @RequestMapping("/userToFight/{id}")
//    public List<FightManagement> userToFight(@PathVariable("id") Integer id, Model model) {
//
//        List<FightManagement> userToFight = fightManagementService.userToFight(id);
//
//        model.addAttribute("userToFight",userToFight);
//
//        return userToFight;
//    }

    /**
     * 查询所有飞机管理信息
     */
    @RequestMapping(value = "/hystrix1", method = RequestMethod.GET)
    public String hystrix1(@Param("num") Integer num) throws Exception {
        return orderServiceFeign.hystrix1(num);
    }


}
