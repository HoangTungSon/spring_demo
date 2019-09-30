package source.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import source.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
