/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo4.g20.servicio;

import Ciclo4.g20.modelo.User;
import Ciclo4.g20.repositorio.UserRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS M509
 */
@Service
public class UserService {

    @Autowired
    private UserRepositorio userRepositorio;

    public List<User> getAll() {
        return userRepositorio.getAll();
    }

    public Optional<User> getUser(int id) {
        return userRepositorio.getUser(id);
    }

    public User save(User user) {
        //Se obtiene el maximo id existente en la coleccion
        Optional<User> userIdMaximo = userRepositorio.lastUserId();
        //si el id del usuario que se recibe como parametro es nulo, entonces valida el maximo id
        if (user.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (userIdMaximo.isEmpty()) {
                user.setId(1);
            } //si retorna informacion suma 1 al maximo id existente y lo asigna como codigo id
            else {
                user.setId(userIdMaximo.get().getId() + 1);
            }
        }

        Optional<User> dbUser = userRepositorio.getUser(user.getId());
        if (dbUser.isEmpty()) {
            if (emailExists(user.getEmail()) == false) {
                return userRepositorio.save(user);
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    public User update(User user) {
        if (user.getId() != null) {
            Optional<User> dbUser = userRepositorio.getUser(user.getId());
            if (!dbUser.isEmpty()) {
                if (user.getIdentification() != null) {
                    dbUser.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    dbUser.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    dbUser.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    dbUser.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    dbUser.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    dbUser.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    dbUser.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    dbUser.get().setType(user.getType());
                }
                userRepositorio.update(dbUser.get());
                return dbUser.get();
            } else {
                return user;
            }
        }
        return user;
    }

    public boolean delete(int userId) {
        Boolean userBoolean = getUser(userId).map(user -> {
            userRepositorio.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }

    public boolean emailExists(String email) {
        return userRepositorio.emailExists(email);
    }

    public User authenticateUser(String email, String password) {
        Optional<User> user = userRepositorio.authenticateUser(email, password);
        if (user.isEmpty()) {
            return new User();
        }
        return user.get();
    }
}
