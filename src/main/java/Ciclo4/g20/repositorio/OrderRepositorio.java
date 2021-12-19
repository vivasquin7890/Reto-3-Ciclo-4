/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo4.g20.repositorio;

import Ciclo4.g20.modelo.Order;
import Ciclo4.g20.repositorio.crud.OrderCrudRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Manuel Naranjo
 */
@Repository
public class OrderRepositorio {
    @Autowired
    private OrderCrudRepositorio orderCrudRepositorio;

    public List<Order> getAll() {
        return orderCrudRepositorio.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudRepositorio.findById(id);
    }

    public Order save(Order order){
        return orderCrudRepositorio.save(order);
    }
    
    public void update(Order order){
        orderCrudRepositorio.save(order);
    }
    
    public void delete(Order order){
        orderCrudRepositorio.delete(order);
    } 
    
    public Optional<Order> LastOrderId(){
        return orderCrudRepositorio.findTopByOrderByIdDesc();
    }
    
    public List<Order> findByZone(String zona){
        return orderCrudRepositorio.findByZone(zona);
    }
}
