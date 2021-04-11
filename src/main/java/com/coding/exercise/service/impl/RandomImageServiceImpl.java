package com.coding.exercise.service.impl;

import com.coding.exercise.client.RandomImageClient;
import com.coding.exercise.client.response.Image;
import com.coding.exercise.exception.ImageNotFoundException;
import com.coding.exercise.service.RandomImageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class RandomImageServiceImpl implements RandomImageService {

  @Autowired private RandomImageClient randomImageClient;

  private List<Image> imageList = new ArrayList<>();

  private Random random = new Random();

  @Override
  public Image getRandomImage() {
    if (CollectionUtils.isEmpty(imageList)) {
      imageList = randomImageClient.getImages();
    }

    if (CollectionUtils.isEmpty(imageList)) throw new ImageNotFoundException();

    return imageList.get(random.nextInt(imageList.size()));
  }
}
