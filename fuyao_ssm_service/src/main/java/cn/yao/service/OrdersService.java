package cn.yao.service;

import cn.yao.domain.Orders;


import java.util.List;

public interface OrdersService {
    List<Orders> findAll(int page,int pageSize);

    Orders findById(String id);
}
