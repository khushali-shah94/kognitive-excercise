package com.coding.exercise.controller.response;

import java.util.List;
import lombok.Data;

@Data
public class Status {
  private StatusEnum status = StatusEnum.OK;
  private List<Details> details;
}
