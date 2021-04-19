package com.eliseu.BikesRegistration.controller;

import com.eliseu.BikesRegistration.dto.BuyerDto;
import com.eliseu.BikesRegistration.model.Buyer;
import com.eliseu.BikesRegistration.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @GetMapping(path = "/buyers")
    public ResponseEntity<List<Buyer>> findAll() {
        List<Buyer> listBikes = buyerService.findAll();
        return ResponseEntity.ok().body(listBikes);
    }

    @GetMapping(path = "/buyers/{id}")
    public ResponseEntity<Buyer> getBikeById(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        Buyer buyer = buyerService.findById(id);
        return ResponseEntity.ok().body(buyer);
    }

    @PostMapping(path = "/buyers")
    public ResponseEntity<?> insert(@Valid @RequestBody BuyerDto buyerDto) throws Exception {
        Buyer buyer = buyerService.insert(buyerDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(buyer.getId()).toUri();
        return ResponseEntity.created(uri).body(buyer);
    }

    @DeleteMapping(path = "/buyers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        buyerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/buyers/{id}")
    public ResponseEntity<Buyer> update(@PathVariable Long id, @RequestBody Buyer buyer) {
        buyer = buyerService.update(id, buyer);
        return ResponseEntity.ok().body(buyer);
    }

}
