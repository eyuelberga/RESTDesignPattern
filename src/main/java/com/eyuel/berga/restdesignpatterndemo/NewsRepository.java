package com.eyuel.berga.restdesignpatterndemo;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsRepository extends PagingAndSortingRepository<NewsDomain, Long> {
}