package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class KitchenRegister {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list(){
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();

    }

    public Kitchen find(Long id){
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    public Kitchen add(Kitchen kitchen){
        return manager.merge(kitchen);
    }

}
