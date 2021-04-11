package com.coding.exercise.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

import com.coding.exercise.ExerciseApplication;
import com.coding.exercise.client.response.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ExerciseApplication.class)
class RandomImageClientTest {

  @Autowired private RandomImageClient randomImageClient;

  private MockRestServiceServer mockServer;

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired private RestTemplate restTemplate;

  @BeforeEach
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void testGetImage() throws JsonProcessingException {

    mockServer
        .expect(ExpectedCount.once(), requestTo("https://jsonplaceholder.typicode.com/photos"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(getImageList())));

    randomImageClient.getImages();

    mockServer.verify();
  }

  private List<Image> getImageList() {
    return LongStream.range(0, 5000)
        .mapToObj(operand -> Image.builder().id(operand).url("http://image-url" + operand).build())
        .collect(Collectors.toList());
  }
}
