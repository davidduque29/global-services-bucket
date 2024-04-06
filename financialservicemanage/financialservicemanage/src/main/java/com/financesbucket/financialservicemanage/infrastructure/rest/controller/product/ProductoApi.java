package com.financesbucket.financialservicemanage.infrastructure.rest.controller.product;

import com.financesbucket.financialservicemanage.application.product.productservice.ProductService;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.product.helper.ProductCreationHelper;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.product.request.ProductRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductoApi {
    private Product product;
    private final ProductService productService;
    private final ProductCreationHelper productcreationHelper;
    private ProductResponse productResponse;

    @PostMapping("/createproduct")
    public ResponseEntity<ProductResponse<Product>> addProductInformation(@RequestBody ProductRequest productRequest) {
        try {
            product = productService.createProduct(productcreationHelper.convertRequestToProduct(productRequest));
            productResponse = productcreationHelper.createResponse(product);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            productResponse = new ProductResponse<>();
            productResponse.setStateMessage(productcreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(productResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findproduct/{id}")
    public ResponseEntity<ProductResponse<Product>> getProductById(@PathVariable("id") Long productId) {
        try {
            var productData = productService.findProductById(productId);
            if (productData.isPresent()) {
                ProductResponse<Product> productResponse = productcreationHelper.createResponse(productData);
                return new ResponseEntity<>(productResponse, HttpStatus.OK);
            } else {
                productResponse = productcreationHelper.createBadResponse();
                return new ResponseEntity<>(productResponse, HttpStatus.NOT_FOUND);
            }
        } catch (BussinessException exc) {
            ProductResponse<Product> productResponse = new ProductResponse<>();
            productResponse.setStateMessage(productcreationHelper.getStateMessages(exc));
            return ResponseEntity.badRequest().body(productResponse);
        }
    }


    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<ProductResponse<Product>> updateProductInformation(@PathVariable("id") Long productId
            , @RequestBody ProductRequest productRequest) {
        try {
            var customer = productService.updateProductId(productId, productcreationHelper
                    .convertRequestToProduct(productRequest));
            productResponse = productcreationHelper.createResponse(customer);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            productResponse = new ProductResponse<>();
            productResponse.setStateMessage(productcreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(productResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/cancelproduct/{id}")
    public ResponseEntity<ProductResponse<Product>> canceledProduct(@PathVariable("id") Long productId
            , @RequestBody ProductRequest productRequest) {
        try {
            var customer = productService.updateProductId(productId, productcreationHelper
                    .convertRequestToProduct(productRequest));
            productResponse = productcreationHelper.createResponse(customer);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            productResponse = new ProductResponse<>();
            productResponse.setStateMessage(productcreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(productResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
