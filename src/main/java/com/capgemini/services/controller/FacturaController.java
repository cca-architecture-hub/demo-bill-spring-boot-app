package com.capgemini.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.capgemini.services.domain.Factura;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
public class FacturaController {

    private final Logger LOG = LoggerFactory.getLogger(FacturaController.class);
    private final List<Factura> objs = new ArrayList<>();

    @GetMapping
    public List<Factura> findAll() {
        return objs;
    }

    @GetMapping("/{id}")
    public Factura findById(@PathVariable("id") Long id) {
        Factura obj = objs.stream().filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow();
        LOG.info("Found: {}", obj.getId());
        return obj;
    }

    @PostMapping
    public Factura add(@RequestBody Factura obj) {
        obj.setId((long) (objs.size() + 1));
        LOG.info("Added: {}", obj);
        objs.add(obj);
        return obj;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Factura obj = objs.stream().filter(it -> it.getId().equals(id)).findFirst().orElseThrow();
        objs.remove(obj);
        LOG.info("Removed: {}", id);
    }

    @PutMapping
    public void update(@RequestBody Factura obj) {
        Factura objTmp = objs.stream()
                .filter(it -> it.getId().equals(obj.getId()))
                .findFirst()
                .orElseThrow();
        objs.set(objs.indexOf(objTmp), obj);
        LOG.info("Updated: {}", obj.getId());
    }

}