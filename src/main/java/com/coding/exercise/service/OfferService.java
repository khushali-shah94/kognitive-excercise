package com.coding.exercise.service;

import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.controller.response.GetOfferResponse;

public interface OfferService {

  void create(CreateOfferRequest createOfferRequest);

  GetOfferResponse get();
}
