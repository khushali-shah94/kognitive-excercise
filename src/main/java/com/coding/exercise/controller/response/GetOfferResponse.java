package com.coding.exercise.controller.response;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class GetOfferResponse {
  private List<Entitlement> entitlements;
  private Status status;
  private EntitlementStatus entitlementStatus = EntitlementStatus.CANCELLED;
}
