//package com.unpla.service.command.impl;
//
//import com.unpla.entity.document.Recycler;
//import com.unpla.model.controller.RecyclerGetListBySubWasteResponse;
//import com.unpla.model.controller.RecyclerGetToRecyclerResponse;
//import com.unpla.model.service.RecyclerGetListBySubWasteRequest;
//import com.unpla.repository.RecyclerRepository;
//import com.unpla.service.command.GetRecyclerListBySubWasteCommand;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Service;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class GetRecyclerListBySubWasteCommandImplTest {
//
//    @Autowired
//    private RecyclerRepository recyclerRepository;
//
//    public Mono<RecyclerGetListBySubWasteResponse> execute(RecyclerGetListBySubWasteRequest request) {
//        return recyclerRepository.findRecyclersBySubWastePriceList(request.getSubWasteCategory())
//                .map(this::toRecyclerWebResponse)
//                .collectList()
//                .map(this::toWebResponse);
//    }
//
//    private RecyclerGetToRecyclerResponse toRecyclerWebResponse(Recycler recycler){
//        return RecyclerGetToRecyclerResponse.builder()
//                .id(recycler.getId())
//                .address(recycler.getAddress())
//                .coordinate(recycler.getCoordinate())
//                .description(recycler.getDescription())
//                .headerPhoto(recycler.getHeaderPhoto())
//                .name(recycler.getName())
//                .profilePhoto(recycler.getProfilePhoto())
//                .mainWastePriceList(recycler.getMainWastePriceList())
//                .subWastePriceList(recycler.getSubWastePriceList())
//                .recycledProductId(recycler.getRecycledProductId())
//                .build();
//    }
//
//    private RecyclerGetListBySubWasteResponse toWebResponse(List<RecyclerGetToRecyclerResponse> recyclers){
//        return RecyclerGetListBySubWasteResponse.builder()
//                .listRecycler(recyclers)
//                .build();
//    }
//}
