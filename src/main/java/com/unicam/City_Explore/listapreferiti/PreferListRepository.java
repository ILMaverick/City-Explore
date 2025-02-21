package com.unicam.City_Explore.listapreferiti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferListRepository extends JpaRepository<PreferList, Integer> {

    PreferList findByUserId(int idUser);
}
