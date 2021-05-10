package com.lob.rest.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lob
 * @description EventRepository
 * @since 2021.03.26
 **********************************************************************************************************************/
public interface EventRepository extends JpaRepository<Event, Integer> {
}
