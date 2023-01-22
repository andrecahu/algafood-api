package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantRegistrationService restaurantRegistrationService;

    @GetMapping
    public List<Restaurant> list(){
        return restaurantRepository.findAll();
    }

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId){

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if(restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant){
        try{
            restaurant =  restaurantRegistrationService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping(value = "/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){
        try{
            Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null);

            if(currentRestaurant != null){

                BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "adress", "registrationDate", "products");

                currentRestaurant = restaurantRegistrationService.save(currentRestaurant);

                return ResponseEntity.ok(currentRestaurant);

            }

            return ResponseEntity.notFound().build();

        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields){

        Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if(currentRestaurant == null){
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentRestaurant);

        return update(restaurantId, currentRestaurant);
    }

    private static void merge(Map<String, Object> fieldsOrigin, Restaurant restaurantDestination) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigin = objectMapper.convertValue(fieldsOrigin, Restaurant.class);

        fieldsOrigin.forEach((propertieName, propertieValue) ->{
            Field field = ReflectionUtils.findField(Restaurant.class, propertieName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field,restaurantOrigin);

            ReflectionUtils.setField(field, restaurantDestination, newValue);
        });
    }

    @DeleteMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> remove(@PathVariable Long restaurantId){
        try {
            restaurantRegistrationService.remove(restaurantId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }



}
