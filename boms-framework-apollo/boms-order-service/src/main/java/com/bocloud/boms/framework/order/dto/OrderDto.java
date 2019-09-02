package com.bocloud.boms.framework.order.dto;

import com.bocloud.boms.framework.order.model.HangBanInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: boms-framework
 * @description: 订单服务调用用户服务
 * @author: guo
 * @create: 2019-05-21
 **/
@Getter
@Setter
@ToString
public class OrderDto extends HangBanInformation {

    String order;

    public OrderDto() {
    }


    public static OrderDto copy(HangBanInformation hangBanInformation){

        OrderDto orderDto = new OrderDto();

        orderDto.setHangBanId(hangBanInformation.getHangBanId());
        orderDto.setBookTicketsTime(hangBanInformation.getBookTicketsTime());
        orderDto.setHangBanCapa(hangBanInformation.getHangBanCapa());
        orderDto.setFightId(hangBanInformation.getFightId());
        orderDto.setId(hangBanInformation.getId());
        return orderDto;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    @Override
    public String toString() {

        return "OrderDto(order=" + this.getOrder() + ")";
    }

}
