package com.coding.exercise.validator;

import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.exception.DataNotValidException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class CreateRequestDateValidator implements Validator<CreateOfferRequest> {

  @Override
  public void validate(CreateOfferRequest createOfferRequest) {
    if(!isToDateAfterFrom(createOfferRequest)){
      throw new DataNotValidException("validTill Date should be after validFrom date");
    }
  }

  private boolean isToDateAfterFrom(CreateOfferRequest createOfferRequest) {
    return createOfferRequest.getValidTo().isAfter(createOfferRequest.getValidFrom());
  }
}
