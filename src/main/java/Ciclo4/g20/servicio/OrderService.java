package Ciclo4.g20.servicio;

import Ciclo4.g20.modelo.Order;
import Ciclo4.g20.repositorio.OrderRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Manuel Naranjo
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepositorio orderRepositorio;

    public List<Order> getAll() {
        return orderRepositorio.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepositorio.getOrder(id);
    }

    public Order save(Order order) {
        //Se obtiene el maximo id existente en la coleccion
        Optional<Order> orderIdMaximo = orderRepositorio.LastOrderId();
        //si el id del usuario que se recibe como parametro es nulo, entonces valida el maximo id
        if (order.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (orderIdMaximo.isEmpty()) {
                order.setId(1);
            } //si retorna informacion suma 1 al maximo id existente y lo asigna como codigo id
            else {
                order.setId(orderIdMaximo.get().getId() + 1);
            }
        }

        Optional<Order> dbOrder = orderRepositorio.getOrder(order.getId());
        if (dbOrder.isEmpty()) {
            return orderRepositorio.save(order);
        } else {
            return order;
        }

    }

    public Order update(Order order) {
        if (order.getId() != null) {
            Optional<Order> dbOrder = orderRepositorio.getOrder(order.getId());
            if (!dbOrder.isEmpty()) {
                if (order.getRegisterDay() != null) {
                    dbOrder.get().setRegisterDay(order.getRegisterDay());
                }
                if (order.getStatus() != null) {
                    dbOrder.get().setStatus(order.getStatus());
                }
                if (order.getSalesMan() != null) {
                    dbOrder.get().setSalesMan(order.getSalesMan());
                }

                orderRepositorio.update(dbOrder.get());
                return dbOrder.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public Boolean delete(int id) {
        return getOrder(id).map(Order -> {
            orderRepositorio.delete(Order);
            return true;
        }).orElse(false);
    }
    
    public List<Order> findByZone(String zona){
        return orderRepositorio.findByZone(zona);
    }
    
//    public List<Order> ordersSalesManByDate(String dateStr, int id){
//        return orderRepositorio.ordersSalesManByDate(dateStr, id);
//    }
//    
//    public List<Order> ordersSalesManByState(String state, Integer id){
//        return orderRepositorio.orderSalesManByState(state, id);
//    }
}
