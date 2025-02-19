package com.speriamochemelacavo.City_Explore.ELEMENT;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository<T extends AbstractElement> extends JpaRepository<T, Integer> {

}
