package com.bocloud.boms.framework.order.service.impl;


import com.bocloud.boms.framework.order.constant.Constant;
import com.bocloud.boms.framework.order.mapper.HangBanInformationMapper;
import com.bocloud.boms.framework.order.model.HangBanInformation;
import com.bocloud.boms.framework.order.service.HangBanInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;

/**
 * @Author guo
 */
@RestController
public class HangBanInformationImpl implements HangBanInformationService {

    @Autowired
    private HangBanInformationMapper hangBanInformationMapper;

      /**
          * 更新版本号
          * @param fightId
          * @param version
          * @return
          */
    @Override
    public HangBanInformation updateVersion(Integer fightId, Integer version) {
        return hangBanInformationMapper.updateVersion(fightId,version);
    }

    @Override
    public List<HangBanInformation> yuTicketsSelectVersion(Integer fightId) {
        return hangBanInformationMapper.yuTicketsSelectVersion(fightId);
    }

    @Override
    public HangBanInformation insertUserAndFightVersion(@RequestBody HangBanInformation hangBanInformation, @PathVariable("id") Integer id, @PathVariable("fightId")Integer fightId) {
        /**
         *  并发测试验证
         *  1.进程走到这会进行排队
         *  2.操作数据库 使用乐观锁
         *  3.获取数据库版本号 version
         *  4.形成订单信息
         *  4.更新数据库（version+1）
         */
        synchronized (this) {
            //查询余票跟版本号
            List<HangBanInformation> selectTicketsAndVersion = hangBanInformationMapper.yuTicketsSelectVersion(fightId);
            long yuPiao = 0;
            int version = 0;
            //获取版本号跟余票
            for (int i = 0; i < selectTicketsAndVersion.size(); i++) {
                yuPiao = selectTicketsAndVersion.get(i).getHangBanCapa();
                version = selectTicketsAndVersion.get(i).getVersion();
            }
            if (yuPiao > 0) {
                hangBanInformation.setFightId(Long.valueOf(fightId + ""));
                hangBanInformation.setId(Long.valueOf(id + ""));
                //生成订单
               hangBanInformationMapper.insertUserAndFightVersion(hangBanInformation,id,fightId);
                /**
                 连接数据库进行更新(version+1,在mybatis中的sql加1)
                 */
                hangBanInformationMapper.updateVersion(fightId,version);
            } else {
                //返回前端无票
                }
            return hangBanInformation;
        }
    }

    /**
     * 根据航班表关联飞机管理信息查询票数余量
     */
    @Override
    public Integer yuTicketsSelect(@PathVariable("id") Integer fightId) {

        return hangBanInformationMapper.yuTicketsSelect(fightId);
    }


    /**
     * 在获取用户信息及飞机信息之后，插入用户id,飞机id及航班随机id及余量及订票时间
     */
    @Override
    public HangBanInformation insertUserAndFight(@RequestBody HangBanInformation hangBanInformation, @PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId) {

        //首先查询余票,调用查询余票方法
        Integer yuPiao = hangBanInformationMapper.yuTicketsSelect(fightId);

//        Integer yuPiao = 22;  //测试

        //判断插入的条件
        if(yuPiao > 0){

            hangBanInformation.setFightId(Long.valueOf(fightId + ""));

            hangBanInformation.setId(Long.valueOf(id + ""));

            Integer hangBanId = hangBanInformationMapper.insertUserAndFight(hangBanInformation);

            hangBanInformation.setHangBanId(Long.valueOf(hangBanId + ""));

        }else{

            System.out.println("暂无余票,禁止插入信息");
        }

        return hangBanInformation;


    }

    /**
     *去航班信息表查询在飞机id下的所有用户的id信息
     */
    @Override
    public List<HangBanInformation> selectUserIdByFightId(@PathVariable("id") Integer fightId) {

        return hangBanInformationMapper.selectUserIdByFightId(fightId);

    }

    /**
     *去航班信息表查询在用户id下的所有飞机的id信息
     */
    @Override
    public List<HangBanInformation> selectFightIdByUserId(@PathVariable("id") Integer id) {

        return hangBanInformationMapper.selectFightIdByUserId(id);

    }

    /**
     * 去航班信息表中实现用户的退订功能（delete）
     */
    @Override
    public Integer deleteUserTicket(@PathVariable("id") Integer id,@PathVariable("fightId") Integer fightId) {

        return hangBanInformationMapper.deleteUserTicket(id,fightId);

    }

    /**
     * 退票功能测试
     */
    @Override
    public Integer deleteTickets(@PathVariable("orderId") Integer hangBanId) {

        return hangBanInformationMapper.deleteTickets(hangBanId);

    }

    @Override
    public List<HangBanInformation> findAllOrders() {

        return hangBanInformationMapper.findAllOrders();
    }




}
