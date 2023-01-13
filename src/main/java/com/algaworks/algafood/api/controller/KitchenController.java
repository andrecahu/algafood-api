package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegistrationService kitchenRegistrationService;

    @GetMapping
    public List<Kitchen> list(){
        return kitchenRepository.findAll();
    }
    
    @GetMapping(value = "/{kitchenId}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long kitchenId){
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

        if(kitchen.isPresent()) {
            return ResponseEntity.ok(kitchen.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen){
        return kitchenRegistrationService.save(kitchen);
    }

    @PutMapping(value = "/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
        Optional<Kitchen> currentKitchen = kitchenRepository.findById(kitchenId);

        if(currentKitchen.isPresent()){
            BeanUtils.copyProperties(kitchen, currentKitchen.get(), "id");

            Kitchen savedKitchen = kitchenRegistrationService.save(currentKitchen.get());

            return ResponseEntity.ok(savedKitchen);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping(value="{kitchenId}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId){
        try {
            kitchenRegistrationService.remove(kitchenId);

            return ResponseEntity.noContent().build();

        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        }catch(EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
