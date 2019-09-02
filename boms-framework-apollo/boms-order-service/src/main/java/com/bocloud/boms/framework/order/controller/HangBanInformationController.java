package com.bocloud.boms.framework.order.controller;

import cn.hutool.core.collection.CollUtil;
import com.bocloud.boms.framework.order.constant.Constant;
import com.bocloud.boms.framework.order.dto.OrderDto;
import com.bocloud.boms.framework.order.feign.UserServiceFeign;
import com.bocloud.boms.framework.order.model.HangBanInformation;
import com.bocloud.boms.framework.order.service.HangBanInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;
import java.util.Random;

/**
 * @Author guo
 */
@RestController
@Slf4j
public class HangBanInformationController implements Runnable {

    @Autowired
    HangBanInformationService hangBanInformationService;

    @Autowired
    Environment environment;

    @Autowired
    UserServiceFeign userServiceFeign;

    /**
     * 导入常量类
     */
    Constant constant;

    /**
     * 并发测试-查询余票+version
     *
     * @param fightId
     * @return
     */

    @RequestMapping("/remainTicket/{id}")
    public List<HangBanInformation> yuTicketsSelectVersion(@PathVariable("id") Integer fightId, Model model) {

        List<HangBanInformation> hangBanInformation = hangBanInformationService.yuTicketsSelectVersion(fightId);

        for (int i = 0; i < hangBanInformation.size(); i++) {

            System.out.println(hangBanInformation.get(i).getHangBanCapa());

            System.out.println(hangBanInformation.get(i).getHangBanCapa());
        }

        model.addAttribute("hangBanInformation", hangBanInformation);

        return hangBanInformation;

    }

    /**
     * 并发测试-插入形成订单+version
     *
     * @param hangBanInformation
     * @return
     */
    @RequestMapping("/insertTickets/{id}/{fightId}")
    public HangBanInformation insertUserAndFightVersion(@RequestBody HangBanInformation hangBanInformation,
                                                        @PathVariable("id") Integer id,
                                                        @PathVariable("fightId") Integer fightId) {
        return hangBanInformationService.insertUserAndFightVersion(hangBanInformation, fightId, id);
    }


    /**
     * 订单服务调用用户服务的测试
     */
    @RequestMapping("/allOrders")
    public List<OrderDto> listOrders(Model model) {
        List<HangBanInformation> list = hangBanInformationService.findAllOrders();
        List<OrderDto> orderDtos = CollUtil.newArrayList();
        if (!CollUtil.isEmpty(list)) {
            list.forEach(orderModel -> {
                OrderDto orderDto = OrderDto.copy(orderModel);
                String order = userServiceFeign.getOrderCountByUserId((int) orderModel.getHangBanId());
                orderDto.setOrder(order);
                orderDtos.add(orderDto);
            });
        }

        model.addAttribute("hangBanInformation", orderDtos);

        return orderDtos;
    }


    /**
     * 根据飞机管理信息id关联航班信息id查询机票余量
     */

    @RequestMapping("/yuPiao/{id}")
    public Integer yuTicketsSelect(@PathVariable("id") Integer fightId, Model model) {

        Integer yuTicketsSelect = hangBanInformationService.yuTicketsSelect(fightId);

        model.addAttribute("yuPiao", yuTicketsSelect);

        return yuTicketsSelect;
    }

    /**
     * 在获取用户信息及飞机信息之后，插入用户id,飞机id及航班随机id及余量及订票时间
     */
    @RequestMapping("/insertUserAndFight/{id}/{fightId}")
    public HangBanInformation insertUserAndFight(@RequestBody HangBanInformation hangBanInformation, @PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId) {

        HangBanInformation insertUserAndFight = hangBanInformationService.insertUserAndFight(hangBanInformation, id, fightId);

        return insertUserAndFight;

    }

    /**
     * 去航班信息表查询在飞机id下的所有用户的id信息
     */

    @RequestMapping("selectUserIdByFightId/{id}")
    public List<HangBanInformation> selectUserIdByFightId(@PathVariable("id") Integer fightId, Model model) {

        List<HangBanInformation> userIdByFightId = hangBanInformationService.selectUserIdByFightId(fightId);

        model.addAttribute("userIdByFightId", "userIdByFightId");

        return userIdByFightId;

    }

    @RequestMapping(value = "flight/count/user/{id}")
    public String getFightCountByUserId(@PathVariable(value = "id") Integer id) {
        log.info("getFightCountByUserId id [{}]", id);
        List<HangBanInformation> flights = hangBanInformationService.selectFightIdByUserId(id);
        String result = "当前服务端口[" + environment.getProperty("local.server.port") + "],航班数";
        if (CollUtil.isEmpty(flights)) {
            result = result + 0;
        } else {
            result = result + flights.size();
        }
        return result;
    }

    /**
     * 去航班服务调用订单服务的测试
     */
    @RequestMapping(value = "user/count/flight/{id}")
    public String getUserCountByFightId(@PathVariable(value = "id") Integer id) {
        log.info("getUserCountByFightId id [{}]", id);
        List<HangBanInformation> users = hangBanInformationService.selectUserIdByFightId(id);
        String result = "当前服务端口[" + environment.getProperty("local.server.port") + "],用户数";
        if (CollUtil.isEmpty(users)) {
            result = result + 0;
        } else {
            result = result + users.size();
        }
//        log.info("测试代码[{}]",1/0);
        return result;
    }

    /**
     * 去航班信息表查询在用户id下的所有机票的id信息
     */

    @RequestMapping("selectFightIdByUserId/{id}")
    public List<HangBanInformation> selectFightIdByUserId(@PathVariable("id") Integer id, Model model) {

        List<HangBanInformation> fightIdByUserId = hangBanInformationService.selectFightIdByUserId(id);

        model.addAttribute("fightIdByUserId", "fightIdByUserId");

        return fightIdByUserId;

    }

    /**
     * 去航班信息表中实现用户的退订功能（delete）
     */
    @RequestMapping("/deleteUserTicket/{id}/{fightId}")
    public Integer deleteUserTicket(@PathVariable("id") Integer id, @PathVariable("fightId") Integer fightId) {

        return hangBanInformationService.deleteUserTicket(id, fightId);

    }

    /**
     * 并发测试(是为了插入版本号version字段)(已运行)
     */
    @RequestMapping("/test")
    public void test() {
        //首先连接数据库
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接(连接数据库)
            conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);

            // 执行查询
            stmt = conn.createStatement();

            String sql;
            String sql2;
            sql2 = "alter table hang_ban_information add version int(8) not null default 0 after id";

            Boolean UPDATE = stmt.execute(sql2);

            System.out.println(UPDATE);

            sql = "select * from hang_ban_information";
            //执行订单查询
            ResultSet rs = stmt.executeQuery(sql);

            //展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int version = rs.getInt("version");
                // 输出数据
                System.out.print("version: " + version);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ;
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) {
                    conn.close();
                }
                ;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * 创建多线程，测试并发验证是否成功(测试订票)
     */
    @Override
    public void run() {

        HangBanInformation hangBanInformation = new HangBanInformation();
        hangBanInformation.setBookTicketsTime("2019/5/28");
        hangBanInformation.setHangBanCapa(10);

        Thread thread1 = new Thread(new HangBanInformationController());
        Thread thread2 = new Thread();
        thread1.start();
        thread2.start();

    }

    @RequestMapping(value = "hystrix1", method = RequestMethod.GET)
    public String hystrix1(@RequestParam("num") Integer num) {
        String result = "";
        if (num > 0 && num <= 100) {
            Random random = new Random();
            Integer n = random.nextInt(100);
            if (num > n) {
                log.info("报错{}", 1 / 0);
            } else {
                result = "true";
            }
        } else {
            result = "请输入正确的num";
        }
        return result;

    }

}


