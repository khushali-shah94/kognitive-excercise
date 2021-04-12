package com.coding.exercise.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

  private Long id;
  private String albumId;
  private String title;
  private String url;
  private String thumbnailUrl;

}
