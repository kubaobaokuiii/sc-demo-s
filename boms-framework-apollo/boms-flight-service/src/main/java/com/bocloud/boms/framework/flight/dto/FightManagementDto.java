package com.bocloud.boms.framework.flight.dto;

import com.bocloud.boms.framework.flight.model.FightManagement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: boms-framework
 * @description: 航班业务类
 * @author: guo
 * @create: 2019-05-21
 **/
@Getter
@Setter
@ToString
public class FightManagementDto extends FightManagement {

    String user;

        public static FightManagementDto copy(FightManagement fightManagement){

            FightManagementDto fightManagementDto = new FightManagementDto();

            fightManagementDto.setFightId(fightManagement.getFightId());
            fightManagementDto.setCapacity(fightManagement.getCapacity());
            fightManagementDto.setEndtime(fightManagement.getEndtime());
            fightManagementDto.setStarttime(fightManagement.getStarttime());
            fightManagementDto.setNumber(fightManagement.getNumber());
            fightManagementDto.setPeople(fightManagement.getPeople());
            fightManagementDto.setRemark(fightManagement.getRemark());
            fightManagementDto.setTime(fightManagement.getTime());

            return fightManagementDto;
        }


}
