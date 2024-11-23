package com.univ.sohwakhaeng.product.api;

import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDetailDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseRequestDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.service.EnterpriseService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.api.dto.ProductRequestDto;
import com.univ.sohwakhaeng.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final EnterpriseService enterpriseService;

    @PostMapping("/public/products")
    public BaseResponse<Void> postProducts(@RequestBody List<ProductRequestDto> dtos) throws EnterpriseNotFoundException {

        for (ProductRequestDto dto : dtos) {
            Enterprise enterprise = enterpriseService.getEnterpriseEntityById(dto.enterpriseId());
            productService.saveProduct(Product.createProduct(dto, enterprise));
        }

        return BaseResponse.success(
                SuccessCode.GET_ENTERPRISE_DETAILS, null);
    }

}