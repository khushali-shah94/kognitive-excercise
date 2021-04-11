package com.coding.exercise.validator;

import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.exception.DataNotValidException;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateRequestDateValidatorTest {

  CreateRequestDateValidator createRequestDateValidator = new CreateRequestDateValidator();

  @Test
  public void testWhenToDateIsBeforeFromDate() {
    Assertions.assertThrows(
        DataNotValidException.class,
        () ->
            createRequestDateValidator.validate(
                CreateOfferRequest.builder()
                    .validFrom(ZonedDateTime.now())
                    .validTo(ZonedDateTime.now().minusDays(1))
                    .build()));
  }

  @Test
  public void testWhenToDateIsAfterFromDate() {
    createRequestDateValidator.validate(
        CreateOfferRequest.builder()
            .validFrom(ZonedDateTime.now())
            .validTo(ZonedDateTime.now().plusDays(1))
            .build());
  }
}
