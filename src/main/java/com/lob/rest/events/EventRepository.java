package com.lob.rest.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lob
 * @description EventRepository
 * @since 2021.03.26
 **********************************************************************************************************************/
public interface EventRepository extends JpaRepository<Event, Integer> {
}
