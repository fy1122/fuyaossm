package cn.yao.dao;

import cn.yao.domain.Orders;
import cn.yao.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results(id="resultMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property ="product",one=@One(select = "cn.yao.dao.ProductDao.findById",fetchType = FetchType.EAGER))
    })
    List<Orders> findAll();
    @Select("select * from orders where id=#{id}")
    @Results(id = "resultById",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property ="product",one=@One(select = "cn.yao.dao.ProductDao.findById",fetchType = FetchType.EAGER)),
            @Result(column = "memberId",property = "member",one=@One(select = "cn.yao.dao.MemberDao.findById",fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "travellers",many = @Many(select="cn.yao.dao.TravellerDao.findByOrdersId",fetchType = FetchType.EAGER))

    })
    Orders findById(String id);
}
