package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegistrationService cityRegistrationService;

    @GetMapping
    public List<City> list(){
        return cityRepository.findAll();
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<City> findById(@PathVariable Long cityId){
        Optional<City> city = cityRepository.findById(cityId);

        if(city.isPresent()){
            return ResponseEntity.ok(city.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city){
        try {
            city = cityRegistrationService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city){
        try{
            City currentCity = cityRepository.findById(cityId).orElse(null);

            if(currentCity != null){
                BeanUtils.copyProperties(city, currentCity, "id");
                currentCity = cityRegistrationService.save(currentCity);
                return ResponseEntity.ok(currentCity);
            }
            return ResponseEntity.notFound().build();

        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<City> remove(@PathVariable Long cityId){
        try {
            cityRegistrationService.remove(cityId);

            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
