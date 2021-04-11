package com.coding.exercise.repository;

import com.coding.exercise.entity.Offer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

}
