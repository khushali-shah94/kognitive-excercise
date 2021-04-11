package com.coding.exercise.controller.request;

import java.time.ZonedDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferRequest {

  @NotBlank(message = "name can not be empty")
  @NotNull(message = "name can not be null")
  private String name;

  @NotNull(message = "valid from date can not be null")
  private ZonedDateTime validFrom;

  @NotNull(message = "valid to date can not be null")
  @Future
  private ZonedDateTime validTo;

  @NotNull(message = "location can not be null")
  private String location;
}
