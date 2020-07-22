package cn.yao.service.impl;

import cn.yao.dao.ProductDao;
import cn.yao.domain.Product;
import cn.yao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao dao;
    @Override
    public List<Product> findALl() {
        return dao.findAll();
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }
}
