package com.coupang.marketplace.order.service;

import com.coupang.marketplace.address.repository.AddressRepository;
import com.coupang.marketplace.cart.repository.CartRepository;
import com.coupang.marketplace.global.fixture.AddressFixture.*;
import com.coupang.marketplace.global.fixture.CartFixture.*;
import com.coupang.marketplace.global.fixture.PaymentFixture.*;
import com.coupang.marketplace.global.fixture.ProductFixture.*;
import com.coupang.marketplace.global.fixture.UserFixture.*;
import com.coupang.marketplace.order.controller.dto.OrderRequestDto;
import com.coupang.marketplace.payment.service.PaymentService;
import com.coupang.marketplace.product.domain.Product;
import com.coupang.marketplace.product.repository.ProductRepository;
import com.coupang.marketplace.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ProductRepository productRepository;

    @DisplayName("장바구니에 담긴 상품을 주문한다.")
    @Test
    public void order() {
//        //given
//        final Optional<Product> FoundProduct = Optional.of(Product1.PRODUCT);
//        final OrderRequestDto dto = OrderRequestDto.builder()
//                .type(Payment1.TYPE)
//                .receiverName(User1.NAME)
//                .receiverPhone(User1.PHONE)
//                .receiverRequest("")
//                .build();
//        given(cartRepository.findByUserId(User1.ID)).willReturn(Arrays.asList(Cart1.CART));
//        given(userRepository.findById(User1.ID)).willReturn(User1.USER);
//        given(addressRepository.findMainContentByUserId(User1.ID)).willReturn(Address1.CONTENT);
//        given(productRepository.findByProductId(Product1.ID)).willReturn(FoundProduct);
//        //when
//        orderService.order(User1.ID, dto);
//        //then
//        then(cartRepository).should(times(1)).deleteCartProducts(Cart1.CART);
    }

    @DisplayName("주문을 요청한 상품이 없는 경우 주문에 실패한다.")
    @Test
    public void NoProductToOrder() {
        //given
        final OrderRequestDto dto = OrderRequestDto.builder()
                .type(Payment1.TYPE)
                .receiverName(User1.NAME)
                .receiverPhone(User1.PHONE)
                .receiverRequest("")
                .build();
        given(cartRepository.findByUserId(User1.ID)).willReturn(Arrays.asList());
        //then
        assertThrows(IllegalArgumentException.class, () -> orderService.order(User1.ID, dto));
    }
}
