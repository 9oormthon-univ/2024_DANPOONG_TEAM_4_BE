package com.univ.sohwakhaeng.payment.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;

import com.siot.IamportRestClient.response.Payment;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.payment.exception.PaymentFailException;
import com.univ.sohwakhaeng.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.ENTERPRISE_NOT_FOUND;
import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.PAYMENT_FAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final IamportClient iamportClient;

    public IamportResponse<Payment> validateIamport(String imp_uid) throws PaymentFailException {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
            log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse());
            return payment;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new PaymentFailException(PAYMENT_FAIL.getMessage());
        }
    }


}
