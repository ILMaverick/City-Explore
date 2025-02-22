package com.unicam.City_Explore.listapreferiti;

import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PreferList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "lista_preferiti_elementi",
            joinColumns = @JoinColumn(name = "lista_preferiti_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id"))
    private List<AbstractElement> elementList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AbstractElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<AbstractElement> elementList) {
        this.elementList = elementList;
    }


}
