/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import com.example.model.orderModel;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ali
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class oderController {
@Autowired
    SessionFactory sessionFactory;

    @PostMapping(value = "/order/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> save(@RequestBody orderModel entity) {
        try {
            Session session = sessionFactory.openSession();
            session.save(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }

    @PostMapping(value = "/order/getAll", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> getAll() {
        try {
            Session session = sessionFactory.openSession();
            List<orderModel> entityList = session.createQuery("From orderModel").list();
            session.flush();
            session.close();
            return ResponseEntity.ok(entityList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
    
    @GetMapping(value = "/order/getOne/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> getOne(@PathVariable(value = "id") long id) {
        try {
            Session session = sessionFactory.openSession();
            orderModel entity = session.get(orderModel.class, id);
            session.flush();
            session.close();
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }

    @PostMapping(value = "/order/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> update(@RequestBody orderModel entity) {
        try {
            Session session = sessionFactory.openSession();
            session.saveOrUpdate(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }
    
    @GetMapping(value = "/order/delete/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        try {
            Session session = sessionFactory.openSession();
            orderModel entity = session.get(orderModel.class, id);
            session.delete(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
}
