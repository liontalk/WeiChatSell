package cn.vitalking.repository;

import cn.vitalking.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {


    private final static String OPEN_ID = "zwx223";

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> list = orderMasterRepository.findByBuyerOpenid(OPEN_ID,pageRequest);
        Assert.assertNotEquals(0,list.getTotalElements());
    }


    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("23");
        orderMaster.setBuyerAmount(new BigDecimal(28.2));
        orderMaster.setBuyerName("好怪");
        orderMaster.setBuyerOpenid(OPEN_ID);
        orderMaster.setBuyerPhone("18700002222");
        orderMaster.setBuyerAddress("中国山西");
        OrderMaster order = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(null, order);

    }

}