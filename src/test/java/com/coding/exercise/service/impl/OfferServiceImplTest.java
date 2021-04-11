package com.coding.exercise.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.coding.exercise.client.response.Image;
import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.controller.response.EntitlementStatus;
import com.coding.exercise.controller.response.GetOfferResponse;
import com.coding.exercise.controller.response.StatusEnum;
import com.coding.exercise.entity.Offer;
import com.coding.exercise.repository.OfferRepository;
import com.coding.exercise.service.OfferService;
import com.coding.exercise.service.RandomImageService;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

  @Mock private OfferRepository offerRepository;

  @Mock private RandomImageService randomImageService;

  @InjectMocks private OfferServiceImpl offerService;

  @Test
  public void testCreateOffer() {
    when(randomImageService.getRandomImage()).thenReturn(new Image());

    when(offerRepository.save(any())).thenReturn(new Offer());

    offerService.create(
        CreateOfferRequest.builder()
            .name("Offer1")
            .validFrom(ZonedDateTime.now())
            .validTo(ZonedDateTime.now().plusDays(1))
            .location("Location")
            .build());
  }

  @Test
  public void testCreateOfferWhenImageNotFound() {
    when(randomImageService.getRandomImage()).thenThrow(new RuntimeException("Image not found"));

    assertThrows(
        RuntimeException.class,
        () ->
            offerService.create(
                CreateOfferRequest.builder()
                    .name("Offer1")
                    .validFrom(ZonedDateTime.now())
                    .validTo(ZonedDateTime.now().plusDays(1))
                    .location("Location")
                    .build()));
  }

  @Test
  public void testCreateOfferWhenDBError() {
    when(randomImageService.getRandomImage()).thenReturn(new Image());

    when(offerRepository.save(any())).thenThrow(new RuntimeException("Error while persist"));

    assertThrows(
        RuntimeException.class,
        () ->
            offerService.create(
                CreateOfferRequest.builder()
                    .name("Offer1")
                    .validFrom(ZonedDateTime.now())
                    .validTo(ZonedDateTime.now().plusDays(1))
                    .location("Location")
                    .build()));
  }

  @Test
  public void testGetOffer(){
    GetOfferResponse offerResponse = offerService.get();
    assertNotNull(offerResponse);
    assertEquals(StatusEnum.OK,offerResponse.getStatus().getStatus());
    assertEquals(EntitlementStatus.CANCELLED,offerResponse.getEntitlementStatus());
  }
}
