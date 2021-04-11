package com.coding.exercise.controller;

import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.controller.response.CreateOfferResponse;
import com.coding.exercise.controller.response.GetOfferResponse;
import com.coding.exercise.controller.response.Status;
import com.coding.exercise.service.OfferService;
import com.coding.exercise.validator.Validator;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OfferController {

  @Autowired
  private OfferService offerService;

  @Autowired
  private List<Validator<CreateOfferRequest>> validatorList;

  @ResponseBody
  @PostMapping(value = "/collect/offer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public CreateOfferResponse createOffer(@Valid @RequestBody CreateOfferRequest createOfferRequest) {
    log.info("Received createOffer request");
    validatorList.forEach(validator -> validator.validate(createOfferRequest));
    offerService.create(createOfferRequest);
    return CreateOfferResponse.builder().success(Boolean.TRUE.toString()).build();
  }


  @ResponseBody
  @GetMapping(value = "/collect/offer")
  public GetOfferResponse getOffer() {
    log.info("Received getOffer request");
    return offerService.get();
  }
}
