//package com.unpla.service.command.impl;
//
//import com.blibli.oss.command.exception.CommandValidationException;
//import com.unpla.entity.document.RecycledProduct;
//import com.unpla.entity.document.Recycler;
//import com.unpla.entity.embedded.Coordinate;
//import com.unpla.entity.embedded.MainWastePrice;
//import com.unpla.entity.embedded.SubWastePrice;
//import com.unpla.entity.enums.Magnitude;
//import com.unpla.entity.enums.SubWasteCategory;
//import com.unpla.model.controller.RecyclerAddResponse;
//import com.unpla.model.service.RecyclerAddRequest;
//import com.unpla.repository.RecyclerRepository;
//import com.unpla.repository.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.validation.ConstraintViolation;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import static com.unpla.entity.enums.Magnitude.Kilogram;
//import static com.unpla.entity.enums.MainWasteCategory.Kaleng;
//import static com.unpla.entity.enums.MainWasteCategory.Plastik;
//import static com.unpla.entity.enums.SubWasteCategory.Botol_Bening;
//import static com.unpla.entity.enums.SubWasteCategory.Botol_Warna;
//import static junit.framework.TestCase.fail;
//import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class AddRecyclerToRecyclerCommandImplTest {
//
//    @MockBean
//    private RecyclerRepository recyclerRepository;  // TODO: sebelumnya repository ini error, (disable inspection)
//
//    @MockBean
//    private AddRecyclerToRecyclerCommandImpl command;
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
//    String photoProfilePath = null;
//
//    private void createRecycler(String id) {
//        subWastePriceList.add(newSubWastePrice);
//        subWastePriceList.add(newSubWastePrice1);
//        mainWastePriceList.add(newMainWastePrice);
//        mainWastePriceList.add(newMainWastePrice1);
//        recycledProductIdList.add("product-001");
//        recycledProductIdList.add("product-002");
//        recycledProductIdList.add("product-003");
//
//        Recycler recycler = Recycler.builder()
//                .id(id)
//                .name("CV Sejahtera Abadi")
//                .address("Jalan Munggur Nomor 4B")
//                .description("Membeli sampah plastik bening (Botol mineral, gelas mineral, dll)")
//                .subWastePriceList(subWastePriceList)
//                .mainWastePriceList(mainWastePriceList)
//                .coordinate(coordinate)
//                .profilePhoto("Photo1.com/123")
//                .headerPhoto("")
//                .recycledProductId(recycledProductIdList)
//                .build();
//        recyclerRepository.save(recycler).block();
//    }
//
//
//    @Test
//    public void testSuccessAddRecycler() throws Exception {
//        String recyclerId = UUID.randomUUID().toString();
//
//        createRecycler(randomUUID);
//
//        RecyclerAddRequest request = RecyclerAddRequest.builder()
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
//        RecyclerAddResponse recycler = command.execute(request).block();
//        Recycler dbRecycler = recyclerRepository.findById(recyclerId).block();
//
//        assertEquals(dbRecycler, recycler);
//    }
//
//    @Test
//    public void testFailedRecyclerAlreadyExists() throws Throwable {
//        String recyclerId = UUID.randomUUID().toString();
//        createRecycler(randomUUID);
//
//        RecyclerAddRequest request = RecyclerAddRequest.builder()
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
//        try {
//            command.execute(request).block();
//            fail("It should be throw exception");
//        } catch (CommandValidationException e) {
//            e.getConstraintViolations().forEach(constraintViolation ->
//                    assertEquals("CartMustNotExists", constraintViolation.getMessage())
//            );
//        }
//    }
//}
