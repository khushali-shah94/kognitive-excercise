package com.coding.exercise.service.impl;

import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.controller.response.GetOfferResponse;
import com.coding.exercise.controller.response.Status;
import com.coding.exercise.entity.Offer;
import com.coding.exercise.repository.OfferRepository;
import com.coding.exercise.service.OfferService;
import com.coding.exercise.service.RandomImageService;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  private final RandomImageService randomImageService;

  @Override
  public void create(CreateOfferRequest createOfferRequest) {
    Offer offer = createOffer(createOfferRequest);
    offer = offerRepository.save(offer);
    log.info("Offer persisted successfully wih id {}", offer.getId());
  }

  @Override
  public GetOfferResponse get() {
    GetOfferResponse offerResponse = new GetOfferResponse();
    Status status = new Status();
    status.setDetails(Collections.emptyList());
    offerResponse.setStatus(status);
    offerResponse.setEntitlements(Collections.emptyList());
    return offerResponse;
  }

  private Offer createOffer(CreateOfferRequest createOfferRequest) {
    return Offer.builder()
        .name(createOfferRequest.getName())
        .validFrom(createOfferRequest.getValidFrom())
        .validTill(createOfferRequest.getValidTo())
        .location(createOfferRequest.getLocation())
        .images(randomImageService.getRandomImage().getUrl())
        .build();
  }
}
