package com.hypad.Market.repositoryImpl;

import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Qualifier("postgresJdbcTemplate")
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("email", email);
        return jdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (:name, :email, :password)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());

        jdbcTemplate.update(sql, namedParameters);
    }
}
