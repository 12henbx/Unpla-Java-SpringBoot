//package com.unpla.repository;
//
//import com.unpla.entity.document.Recycler;
//import com.unpla.entity.embedded.Coordinate;
//import com.unpla.entity.embedded.MainWastePrice;
//import com.unpla.entity.embedded.SubWastePrice;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static com.unpla.entity.enums.Magnitude.Kilogram;
//import static com.unpla.entity.enums.MainWasteCategory.Kaleng;
//import static com.unpla.entity.enums.MainWasteCategory.Plastik;
//import static com.unpla.entity.enums.SubWasteCategory.Botol_Bening;
//import static com.unpla.entity.enums.SubWasteCategory.Botol_Warna;
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class RecyclerRepositoryTest {
//
//    @Autowired
//    private RecyclerRepository recyclerRepository;
//
//    private List<SubWastePrice> subWastePriceList = new ArrayList<>();
//    private List<MainWastePrice> mainWastePriceList = new ArrayList<>();
//    private List<String> recycledProductIdList = new ArrayList<>();
//    SubWastePrice newSubWastePrice = SubWastePrice.builder().subWasteCategory(Botol_Bening).magnitude(Kilogram).price(12400).build();
//    SubWastePrice newSubWastePrice1 = SubWastePrice.builder().subWasteCategory(Botol_Warna).magnitude(Kilogram).price(15400).build();
//    MainWastePrice newMainWastePrice = MainWastePrice.builder().mainWasteCategory(Plastik).magnitude(Kilogram).price(12400).build();
//    MainWastePrice newMainWastePrice1 = MainWastePrice.builder().mainWasteCategory(Kaleng).magnitude(Kilogram).price(13500).build();
//    Coordinate coordinate = Coordinate.builder().latitude((float) 120.597).longitude((float) 144.124).build();
//
//    String randomUUID = UUID.randomUUID().toString();
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
//        Recycler recycler = Recycler.builder()
//                .address(randomUUID)
//                .coordinate(coordinate)
//                .description("Membeli sampah plastik bening (Botol mineral, gelas mineral, dll)")
//                .headerPhoto("")
//                .profilePhoto("Photo1.com/123")
//                .mainWastePriceList(mainWastePriceList)
//                .subWastePriceList(subWastePriceList)
//                .name("CV Sejahtera Abadi")
//                .recycledProductId(recycledProductIdList)
//                .build();
//
//        Recycler result = recyclerRepository.save(recycler).block();
//
//        assertEquals(recycler, result);
//    }
//
//}
