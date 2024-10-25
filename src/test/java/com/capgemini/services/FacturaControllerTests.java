package com.capgemini.services;

import org.instancio.Instancio;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.capgemini.services.domain.Factura;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FacturaControllerTests {

    private static final String API_PATH = "/api/v1/bill";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void add() {
        Factura obj = restTemplate.postForObject(API_PATH, Instancio.create(Factura.class), Factura.class);
        assertNotNull(obj);
        assertEquals(1, obj.getId());
    }

    @Test
    @Order(2)
    void findAll() {
        Factura[] objs = restTemplate.getForObject(API_PATH, Factura[].class);
        assertTrue(objs.length > 0);
    }

    @Test
    @Order(2)
    void findById() {
        Factura obj = restTemplate.getForObject(API_PATH + "/{id}", Factura.class, 1L);
        assertNotNull(obj);
        assertEquals(1, obj.getId());
    }

    @Test
    @Order(3)
    void delete() {
        restTemplate.delete(API_PATH + "/{id}", 1L);
        Factura obj = restTemplate.getForObject(API_PATH + "/{id}", Factura.class, 1L);
        assertNull(obj.getId());
    }

}