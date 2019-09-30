package source.service;

import source.model.Product;

public interface ProductService {
    Iterable<Product> findAll();

    void save(Product product);

    Product findById(Long id);

    void remove(Long id);

}
