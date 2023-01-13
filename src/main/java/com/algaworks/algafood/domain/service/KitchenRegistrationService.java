package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegistrationService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId){
        try {
            kitchenRepository.deleteById(kitchenId);

        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("There is no kitchen with code %d", kitchenId));

        }catch(DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("Kitchen with code %d is in use and cannot be removed", kitchenId));
        }
    }

}
