/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo4.g20.repositorio.crud;

import Ciclo4.g20.modelo.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author ASUS M509
 */
public interface UserCrudRepositorio extends MongoRepository<User, Integer>{
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByNameOrEmail(String email, String password);
    //Para seleccionar el usuario con el id max
    Optional<User> findTopByOrderByIdDesc();
}
