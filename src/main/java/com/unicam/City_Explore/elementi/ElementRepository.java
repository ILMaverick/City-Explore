package com.unicam.City_Explore.elementi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository<T extends AbstractElement> extends JpaRepository<T, Integer> {

}
