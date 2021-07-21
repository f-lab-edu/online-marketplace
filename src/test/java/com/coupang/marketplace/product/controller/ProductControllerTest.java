package com.coupang.marketplace.product.controller;


import com.coupang.marketplace.global.util.MultiValueMapConverter;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static com.coupang.marketplace.product.constant.DeliveryTypeEnum.ROCKET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends ControllerTest {

    @BeforeAll
    public void setUp(){

    }

    @DisplayName("로켓 배송을 선택하면 로켓 배송 가능 상품 목록을 보여준다.")
    @Test
    public void getProductsByIsRocket() throws Exception {
        // given
        final GetProductsRequest dto = GetProductsRequest.builder()
                .deliveryType(ROCKET)
                .page(1)
                .listSize(2)
                .build();
        final MultiValueMap<String, String> params = MultiValueMapConverter.convert(objectMapper, dto);

        // when
        final ResultActions actions = mvc.perform(get("/products")
            .params(params))
            .andDo(print());;

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }
}
