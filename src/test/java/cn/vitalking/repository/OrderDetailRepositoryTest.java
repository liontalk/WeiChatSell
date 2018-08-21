package cn.vitalking.repository;

import cn.vitalking.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {


    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> list = orderDetailRepository.findByOrderId("123");
        Assert.assertNotEquals(null, list);
    }


    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("234");
        orderDetail.setProductId("234");
        orderDetail.setOrderId("234");
        orderDetail.setProductIcon("http://234.jpg");
        orderDetail.setProductName("红烧鱼");
        orderDetail.setProductQuantity(10);
        orderDetail.setProductPrice(new BigDecimal(25.8));
        OrderDetail order = orderDetailRepository.save(orderDetail);
        Assert.assertNotEquals(null, order);
    }


}