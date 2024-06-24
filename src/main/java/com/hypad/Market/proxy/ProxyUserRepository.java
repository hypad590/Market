package com.hypad.Market.proxy;

import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import com.hypad.Market.repositoryImpl.UserRepositoryImpl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ProxyUserRepository implements UserRepository {

    private UserRepositoryImpl userRepositoryImpl;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProxyUserRepository(UserRepositoryImpl userRepositoryImpl, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.userRepositoryImpl = userRepositoryImpl;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User getUserByEmail(String email) {
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: getting user by email: " + email);
        return userRepositoryImpl.getUserByEmail(email);
    }

    @Override
    public User getUserByName(String name) {
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: getting user by name: " + name);
        return userRepositoryImpl.getUserByName(name);
    }

    @Override
    public User getUserById(int id) {
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: getting user by id: " + id);
        return userRepositoryImpl.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: getting users");
        return userRepositoryImpl.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: saving user: " + user.toString());
        userRepositoryImpl.saveUser(user);
    }
}
