package com.univ.sohwakhaeng.payment.api;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import com.univ.sohwakhaeng.payment.exception.PaymentFailException;
import com.univ.sohwakhaeng.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/public/payment/{impUid}")
    public BaseResponse<IamportResponse<Payment>> validateIamport(@PathVariable String impUid) throws PaymentFailException {
        return BaseResponse.success(SuccessCode.POST_PAYMENT, paymentService.validateIamport(impUid));
    }

}
