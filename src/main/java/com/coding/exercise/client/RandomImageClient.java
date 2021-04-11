package com.coding.exercise.client;

import com.coding.exercise.client.response.Image;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class RandomImageClient {

  private final RestTemplate restTemplate;

  //@Cacheable("randomImages")
  public List<Image> getImages() {
    log.info("Fetching images");
    ResponseEntity<List<Image>> responseEntity =
        restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/photos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Image>>() {});

    return responseEntity.getStatusCode().is2xxSuccessful()
        ? Objects.requireNonNull(responseEntity.getBody())
        : Collections.emptyList();
  }
}
