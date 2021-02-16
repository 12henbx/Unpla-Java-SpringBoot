//package com.unpla.service.command.impl;
//
//import com.blibli.oss.command.exception.CommandValidationException;
//import com.unpla.entity.document.Recycler;
//import com.unpla.entity.document.User;
//import com.unpla.model.controller.UserGetRecyclerIdResponse;
//import com.unpla.model.service.UserGetRecyclerIdRequest;
//import com.unpla.repository.UserRepository;
//import com.unpla.service.command.GetRecyclerIdByUserIdCommand;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Service;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import reactor.core.publisher.Mono;
//
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class GetRecyclerIdByUserIdCommandImplTest { // Sudah Selesai yang file ini
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private GetRecyclerIdByUserIdCommandImpl getRecyclerIdByUserIdCommand;
//
//    private void createUser(String id){
//        User user = User.builder()
//                .id(id)
//                .recyclerId("recycler-1234")
//                .build();
//
//        userRepository.save(user).block();
//    }
//
//    @Test
//    public void testSuccessGetRecyclerId() throws Exception {
//        String recyclerId = "recycler-1234";
//        String userId = UUID.randomUUID().toString();
//
//        createUser(userId);
//
//        UserGetRecyclerIdRequest request = UserGetRecyclerIdRequest.builder()
//                .userId(userId)
//                .build();
//
//        UserGetRecyclerIdResponse recycler = getRecyclerIdByUserIdCommand.execute(request).block();
//
//        assertEquals(recyclerId, recycler.getRecyclerId());
//    }
//
//    @Test
//    public void testFailedGetRecyclerId() throws Throwable {
//        String recyclerId = "not-exist";
//        String userId = "no-userid";
//
//        UserGetRecyclerIdRequest request = UserGetRecyclerIdRequest.builder()
//                .userId(userId)
//                .build();
//
//        try {
//            getRecyclerIdByUserIdCommand.execute(request).block();
//            fail("It should be throw exception");
//        } catch (CommandValidationException e) {
//            e.getConstraintViolations().forEach(constraintViolation ->
//                    assertEquals("CartMustNotExists", constraintViolation.getMessage())
//            );
//        }
//    }
//}
