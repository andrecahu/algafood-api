package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegistrationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city){
        Long stateId = city.getState().getId();
        State state = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(
                String.format("A city with code %d doesn't exist",stateId)));

        city.setState(state);

        return cityRepository.save(city);
    }

    public void remove(Long cityId){
        try{
            cityRepository.deleteById(cityId);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("There is no City with code %d", cityId));
        }catch(DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("City with code %d is in use and cannot be removed", cityId));
        }
    }

}
