package com.bocloud.boms.framework.order.service;
import com.bocloud.boms.framework.order.model.HangBanInformation;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public interface HangBanInformationService {

    /**
     * 更新版本号
     */
    HangBanInformation updateVersion(Integer fightId,Integer version);

    /**
     * 并发测试-查询余票+version
     * @param fightId
     * @return
     */
    @GetMapping("/hangBan/version/{id}")
    List<HangBanInformation> yuTicketsSelectVersion(@PathVariable("id") Integer fightId);

    /**
     * 并发测试-插入形成订单+version
     * @param hangBanInformation
     * @return
     */
    @RequestMapping(value = "/hangBan/addVersion/{id}/{fightId}", method = RequestMethod.POST)
    HangBanInformation insertUserAndFightVersion(@RequestBody HangBanInformation hangBanInformation, @PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId);


    /**
     * 根据航班表关联飞机管理信息查询票数余量
     */
    @GetMapping("/hangBan/{id}")
    Integer yuTicketsSelect(@PathVariable("id") Integer fightId);

    /**
     * 在获取用户信息及飞机信息之后，插入用户id,飞机id及航班随机id及余量及订票时间
     */
    @RequestMapping(value = "/hangBan/add/{id}/{fightId}", method = RequestMethod.POST)
    HangBanInformation insertUserAndFight(@RequestBody HangBanInformation hangBanInformation, @PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId);

    /**
     * 去航班信息表查询在飞机id下的所有用户的id信息
     */
    @GetMapping("/hangBanUser/{id}")
    List<HangBanInformation> selectUserIdByFightId(@PathVariable("id") Integer fightId);

    /**
     * 去航班信息表查询在用户id下的所有飞机的id信息
     * @param id
     * @return
     */
    @GetMapping("/hangBanFight/{id}")
    List<HangBanInformation> selectFightIdByUserId(@PathVariable("id") Integer id);

    /**
     * 去航班信息表中实现用户的退订功能（delete）
     */
    @DeleteMapping("/delete/{id}/{fightId}")
    Integer deleteUserTicket(@PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId);

    /**
     * 退票功能测试
     */
    @DeleteMapping("/delete/{orderId}")
    Integer deleteTickets(@PathVariable("orderId") Integer hangBanId);

    /**
     * 查询所有的订单信息
     */
    @GetMapping("/order")
    List<HangBanInformation> findAllOrders();
}
