package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegistrationService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("A kitchen with code %d doesn't exist",kitchenId)));

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

    public void remove(Long restaurantId){
        try{
            restaurantRepository.deleteById(restaurantId);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("There is no Restaurant with code %d", restaurantId));
        }catch(DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("Restaurant with code %d is in use and cannot be removed", restaurantId));
        }

    }


}
