//package com.unpla.repository;
//
//import com.unpla.entity.document.WasteItem;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import reactor.core.publisher.Flux;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class WasteItemRepositoryTest {
//
//    private String randomUUID = UUID.randomUUID().toString();
//    private List<String> photoListPath = new ArrayList<>();
//
//    @Test
//    public void testSaveRecycler() throws Exception {
//        subWastePriceList.add(newSubWastePrice);
//        subWastePriceList.add(newSubWastePrice1);
//        mainWastePriceList.add(newMainWastePrice);
//        mainWastePriceList.add(newMainWastePrice1);
//        recycledProductIdList.add("product-001");
//        recycledProductIdList.add("product-002");
//        recycledProductIdList.add("product-003");
//
//        WasteItem wasteItem = WasteItem.builder()
//                .id(randomUUID)
//                .photoListPath()
//                .weightValue()
//                .magnitude()
//                .subWasteCategory()
//                .mainWasteCategory()
//                .userId()
//                .build();
//
//        Recycler result = recyclerRepository.save(recycler).block();
//
//        assertEquals(recycler, result);
//    }
//}
