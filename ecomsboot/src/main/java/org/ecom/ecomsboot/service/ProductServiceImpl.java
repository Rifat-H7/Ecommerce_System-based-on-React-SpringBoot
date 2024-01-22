package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Category;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.repository.CategoryRepository;
import org.ecom.ecomsboot.repository.ProductRepository;
import org.ecom.ecomsboot.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel=categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevel==null){
            Category topLevelCategory= new Category();
            topLevelCategory.setLevel(1);
            topLevelCategory=categoryRepository.save(topLevelCategory);
        }
        Category secondLevel=categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel == null) {

            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }
        Category thirdLevel=categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel == null) {

            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }
        Product product =new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountedPercentage(req.getDiscountedPercentage());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedOn(LocalDateTime.now());
        Product savedProduct=productRepository.save(product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = getProduct(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = getProduct(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) throws ProductException {
        Optional<Product>opt=productRepository.findById(productId);
        if(!opt.isPresent()){
            throw new ProductException("Product not found with id: "+productId);
        }
        return opt.get();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNumber,pageSize);
        List<Product>products=productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        if (colors != null && !colors.isEmpty()) {
            products=products.stream().filter(product -> colors.stream().anyMatch((c->c.equalsIgnoreCase(product.getColor())))).toList();
        }
        if (stock!=null){
            if(stock.equals("in_stock")){
                products=products.stream().filter(product -> product.getQuantity()>0).toList();
            }
            else if(stock.equals("out-of-stock")){
                products=products.stream().filter(product -> product.getQuantity()==0).toList();
            }
        }
        int startIndex=(int) pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product>pageContent=products.subList(startIndex,endIndex);
        Page<Product>filteredProducts=new PageImpl<>(pageContent,pageable,products.size());
        return filteredProducts;
    }
}
