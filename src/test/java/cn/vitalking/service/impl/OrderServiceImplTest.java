package cn.vitalking.service.impl;

import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {

    private final String OPEN_ID = "zwx534";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("中国北京");
        orderDTO.setBuyerName("周小怪");
        orderDTO.setBuyerOpenid(OPEN_ID);
        orderDTO.setBuyerPhone("18700001111");

        List<OrderDetail> list = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(2);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("234");
        orderDetail2.setProductQuantity(5);
        list.add(orderDetail2);
        list.add(orderDetail);
        orderDTO.setList(list);
        OrderDTO dto =  orderService.create(orderDTO);
        log.info("创建订单===》dto={}",dto);
        Assert.assertNotNull(dto);
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}