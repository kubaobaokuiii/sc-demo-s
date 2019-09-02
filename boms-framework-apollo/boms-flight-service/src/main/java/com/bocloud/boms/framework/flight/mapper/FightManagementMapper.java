package com.bocloud.boms.framework.flight.mapper;


import com.bocloud.boms.framework.flight.model.FightManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("fightManagementMapper")
public interface FightManagementMapper {

    //根据id删除飞机管理信息
    void FightDeleteById(Integer id);

    //插入飞机管理信息
    int insertFight(FightManagement fightManagement);

    //查找飞机管理信息
    FightManagement fightId(Integer id);

    //更新飞机管理信息
    void FightUpdate(FightManagement fightManagement);

    //查找所有飞机管理信息
    List<FightManagement> findAllFight();

////    根据用户查询所对应的机票信息
//    List<FightManagement> userToFight(Integer id);


}
