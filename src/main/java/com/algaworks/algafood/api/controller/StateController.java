package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegistrationService stateRegistrationService;

    @GetMapping
    public List<State> list(){
        return stateRepository.findAll();
    }

    @GetMapping(value = "{stateId}")
    public ResponseEntity<State> findById(@PathVariable Long stateId){
        Optional<State> state = stateRepository.findById(stateId);
        if(state.isPresent()){
            return ResponseEntity.ok(state.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody State state){
        return stateRegistrationService.save(state);
    }

    @PutMapping(value = "{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state){
        State currentState = stateRepository.findById(stateId).orElse(null);

        if(currentState != null){
            BeanUtils.copyProperties(state, currentState, "id");
            currentState = stateRegistrationService.save(currentState);
            return ResponseEntity.ok(currentState);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{stateId}")
    public ResponseEntity<State> remove(@PathVariable Long stateId){
        try{
            stateRegistrationService.remove(stateId);

            return ResponseEntity.noContent().build();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
