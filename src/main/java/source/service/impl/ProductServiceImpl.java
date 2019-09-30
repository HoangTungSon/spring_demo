package source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import source.model.Product;
import source.repository.ProductRepository;
import source.service.ProductService;

public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }
}
