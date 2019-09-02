package com.bocloud.boms.framework.order.mapper;
import com.bocloud.boms.framework.order.model.HangBanInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * guo
 */
@Mapper
@Component("hangBanInformation")
public interface HangBanInformationMapper {

    /**
     * 更新版本号
     * @param fightId
     * @param version
     * @return
     */
    HangBanInformation updateVersion(Integer fightId,Integer version);

    /**
     * 并发测试-查询余票+version
     * @param fightId
     * @return
     */
    List<HangBanInformation> yuTicketsSelectVersion(Integer fightId);

    /**
     * 并发测试-插入形成订单+version
     * @param hangBanInformation
     * @return
     */
    HangBanInformation insertUserAndFightVersion(@RequestBody HangBanInformation hangBanInformation,
                                                 @PathVariable("id") Integer id,
                                                 @PathVariable("fightId") Integer fightId);


    /**
     * 根据航班信息表查找余票信息
     */

    Integer yuTicketsSelect(Integer fightId);

    /**
     * 在获取用户信息及飞机信息之后，插入用户id,飞机id及航班随机id及余量及订票时间
     */
    int insertUserAndFight(@RequestBody HangBanInformation hangBanInformation);

    /**
     * 去航班信息表查询在飞机id下的所有用户的id信息
     */
    List<HangBanInformation> selectUserIdByFightId(Integer fightId);

    /**
     * 去航班信息表查询在用户id下的所有飞机的id信息
     */
    List<HangBanInformation> selectFightIdByUserId(Integer id);

    /**
     * 去航班信息表中实现用户的退订功能（delete）
     */
    Integer deleteUserTicket(Integer id, Integer fightId);

    /**
     * 退票测试
     */
    Integer deleteTickets(Integer hangBanId);

    /**
     * 查询所有订单信息
     */
    List<HangBanInformation> findAllOrders();


}
