package com.coding.exercise.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import com.coding.exercise.ExerciseApplication;
import com.coding.exercise.controller.request.CreateOfferRequest;
import com.coding.exercise.repository.OfferRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ExerciseApplication.class)
@AutoConfigureMockMvc
class OfferControllerTest {

  @Autowired private MockMvc mvc;

  @Autowired private OfferRepository offerRepository;

  @Test
  public void testWhenCreateOfferShouldStoreOfferInDB() throws Exception {

    CreateOfferRequest createOfferRequest =
        createOfferRequest("Offer Test");

    mvc.perform(
            post("/collect/offer")
                .header("Authorization","Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonMapper().writeValueAsString(createOfferRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is("true")));

    assertNotNull(offerRepository.findById(1L));
  }


  @Test
  public void testNotAuthorisedRequest() throws Exception {

    CreateOfferRequest createOfferRequest =
        createOfferRequest("Offer Test");

    mvc.perform(
        post("/collect/offer")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(jsonMapper().writeValueAsString(createOfferRequest)))
        .andExpect(status().is4xxClientError());

  }

  @Test
  public void testCreateOfferWhenNameIsNull() throws Exception {

    CreateOfferRequest createOfferRequest =
        createOfferRequest(null);
    mvc.perform(
        post("/collect/offer")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(jsonMapper().writeValueAsString(createOfferRequest)))
        .andExpect(status().is4xxClientError());

  }

  @Test
  public void testCreateOfferWhenDateIsNotValid() throws Exception {

    CreateOfferRequest createOfferRequest =
        createOfferRequest("Offer Test");

    createOfferRequest.setValidFrom(ZonedDateTime.now().plusDays(2));
    createOfferRequest.setValidTo(ZonedDateTime.now().plusDays(1));

    mvc.perform(
        post("/collect/offer")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(jsonMapper().writeValueAsString(createOfferRequest)))
        .andExpect(status().is4xxClientError());

  }

  @Test
  public void testGetOffer() throws Exception {

    mvc.perform(
        get("/collect/offer")
            .header("Authorization","Basic YWRtaW46YWRtaW4=")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.entitlementStatus").value("CANCELLED"))
        .andExpect(jsonPath("$.status.status").value("OK"));
  }

  @Test
  public void testGetOfferXMLResponse() throws Exception {

    mvc.perform(
        get("/collect/offer")
            .header("Authorization","Basic YWRtaW46YWRtaW4=")
            .contentType(MediaType.APPLICATION_XML)
            .accept(MediaType.APPLICATION_XML))
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("GetOfferResponse/entitlementStatus").string("CANCELLED"))
        .andExpect(xpath("GetOfferResponse/status/status").string("OK"));

  }

  private CreateOfferRequest createOfferRequest(String name) {
    return CreateOfferRequest.builder()
        .name(name)
        .validTo(ZonedDateTime.now().plusDays(1))
        .validFrom(ZonedDateTime.now())
        .location("Location Test")
        .build();
  }


  public JsonMapper jsonMapper(){
    return JsonMapper.builder()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .addModule(new JavaTimeModule())
        .build();
  }
}
