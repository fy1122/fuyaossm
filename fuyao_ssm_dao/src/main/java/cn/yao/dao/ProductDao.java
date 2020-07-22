package cn.yao.dao;

import cn.yao.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {
    /**
     * 查询所有的方法
     */
    @Select("select * from product")
     List<Product> findAll();
    @Select("select * from product where id=#{id}")
    Product findById(String id);
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
    @Delete("delete from product where id=#{id}")
    void delete (String id);
}
