package com.mlj.product.service;

import com.mlj.product.entity.Product;

public interface ProductService {
    /**
     * 根据ID查询
     */
    Product findById(Long id);
    /**
     * 保存
     */
    void save(Product product);
    /**
     * 更新
     */
    void update(Product product);
    /**
     * 删除
     */
    void delete(Long id);

}
