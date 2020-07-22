package cn.yao.service;


import cn.yao.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findALl();

    void save(Product product);

    void delete(String id);
}
