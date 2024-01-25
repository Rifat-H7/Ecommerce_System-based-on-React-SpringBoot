package org.ecom.ecomsboot.Controller;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.request.CreateProductRequest;
import org.ecom.ecomsboot.response.ApiResponse;
import org.ecom.ecomsboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req) {

        Product product = productService.createProduct(req);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
        productService.deleteProduct(productId);
        ApiResponse res=new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>>findAllProducts(){
        List<Product> products=productService.findAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product req) throws ProductException {

        Product product = productService.updateProduct(productId, req);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }
    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
        ApiResponse res=new ApiResponse();

        for(CreateProductRequest product:req) {
            productService.createProduct(product);
            res.setMessage("product deleted successfully");
            res.setStatus(true);
        }
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
