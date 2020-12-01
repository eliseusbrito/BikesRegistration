package com.eliseu.BikesRegistration.endpoint;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.service.BikeService;
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
public class BikeEndPoint {
    @Autowired
    private BikeService bikeService;

    @GetMapping(path = "/bikes")
    public ResponseEntity<List<Bike>> findAll() {
        List<Bike> listBikes = bikeService.findAll();
        return ResponseEntity.ok().body(listBikes);
    }

    @GetMapping(path = "/bikes/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        Bike bike = bikeService.findById(id);
        return ResponseEntity.ok().body(bike);
    }

    @PostMapping(path = "/bikes")
    public ResponseEntity<?> insert(@Valid @RequestBody Bike bike) {
        bike = bikeService.insert(bike);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bike.getId()).toUri();
        return ResponseEntity.created(uri).body(bike);
    }

    @DeleteMapping(path = "/bikes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        bikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/bikes/{id}")
    public ResponseEntity<Bike> update(@PathVariable Long id, @RequestBody Bike bike) {
        bike = bikeService.update(id, bike);
        return ResponseEntity.ok().body(bike);
    }

    @PatchMapping(path = "/bikes/{id}")
    public ResponseEntity<Bike> updatePatch(@PathVariable Long id, @RequestBody Bike bike) {
        bike = bikeService.updatePatch(id, bike);
        return ResponseEntity.ok().body(bike);
    }
}
