package com.coding.exercise.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.coding.exercise.client.RandomImageClient;
import com.coding.exercise.client.response.Image;
import com.coding.exercise.exception.ImageNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RandomImageServiceImplTest {

  @Mock
  private RandomImageClient randomImageClient;

  @InjectMocks
  private RandomImageServiceImpl randomImageService;

  @Test
  public void testGetRandomImage(){
    when(randomImageClient.getImages()).thenReturn(getImageList());
    Image randomImage = randomImageService.getRandomImage();
    assertNotNull(randomImage);
    assertNotNull(randomImage.getUrl());
  }



  @Test
  public void testCacheWhenGetImageCalledMultipleTimes(){
    when(randomImageClient.getImages()).thenReturn(getImageList());
    Image randomImage = randomImageService.getRandomImage();
    assertNotNull(randomImage);
    assertNotNull(randomImage.getUrl());

    randomImage = randomImageService.getRandomImage();
    assertNotNull(randomImage);

    randomImageService.getRandomImage();

    verify(randomImageClient, atMostOnce()).getImages();
  }

  @Test
  public void testRandomnessOfGetRandomImage(){
    when(randomImageClient.getImages()).thenReturn(getImageList());
    Image randomImage = randomImageService.getRandomImage();
    assertNotNull(randomImage);
    assertNotNull(randomImage.getUrl());

    Image randomImage1 = randomImageService.getRandomImage();
    assertNotNull(randomImage);
    assertNotEquals(randomImage.getId(),randomImage1.getId());
  }


  @Test
  public void testWhenImageNotFound(){
    when(randomImageClient.getImages()).thenReturn(Collections.emptyList());
    assertThrows(ImageNotFoundException.class, () ->randomImageService.getRandomImage());
  }


  private List<Image> getImageList(){
    return LongStream.range(0,5000)
        .mapToObj(operand -> Image.builder().id(operand).url("http://image-url"+operand).build())
        .collect(Collectors.toList());
  }


}