package com.univ.sohwakhaeng.product.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.exception.ProductNotFoundException;
import com.univ.sohwakhaeng.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String awsBucket;

    @Transactional(readOnly = true)
    public Product getProductById(Long id) throws ProductNotFoundException {
        return findProductById(id);
    }

    private Product findProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND.getMessage()));
    }

    public String getProductImageUrl(String productName) {
        return amazonS3Client.getUrl(awsBucket, productName).toString();
    }

}
