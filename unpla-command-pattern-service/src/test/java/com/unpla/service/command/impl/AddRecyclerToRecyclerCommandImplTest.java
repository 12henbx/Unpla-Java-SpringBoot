package com.unpla.service.command.impl;

import com.unpla.repository.RecyclerRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AddRecyclerToRecyclerCommandImplTest {

//    @Autowired
//    RecyclerRepository recyclerRepository;
//
//    String randomUUID = "";
//    String photoProfilePath = null;

//    @Test
//    public void testAddProductToNonExistsCart() throws Exception {
//        String cartId = "not-exists";
//        String productId = "sample-product-id";
//
//        createProduct(productId);
//
//        AddProductToCartRequest request = AddProductToCartRequest.builder()
//                .cartId(cartId)
//                .productId(productId)
//                .quantity(10)
//                .build();
//
//        try {
//            command.execute(request).block();
//            fail("it should thrown exception");
//        } catch (CommandValidationException ex) {
//            List<String> messages = ex.getConstraintViolations().stream()
//                    .map(ConstraintViolation::getMessage).collect(Collectors.toList());
//            assertThat(messages, hasItems("CartMustExists"));
//        }
//    }
}
