package cn.yao.service.impl;

import cn.yao.dao.OrdersDao;
import cn.yao.domain.Orders;
import cn.yao.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao dao;

    @Override
    public List<Orders> findAll(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }

    @Override
    public Orders findById(String id) {
      return   dao.findById(id);
    }
}
