package com.coding.exercise.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OFFER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column private ZonedDateTime validFrom;

  @Column private ZonedDateTime validTill;

  @Column private String location;

  @Column private String images;
}
