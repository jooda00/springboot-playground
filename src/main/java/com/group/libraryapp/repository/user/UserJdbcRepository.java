package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// sql 통해서 실제 db와 통신
// spring bean으로 등록
@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, int age) {
        String sql = "insert into user (name, age) values(?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    public void updateUserName(String name, Long id) {
        String sql = "update user set name = ? where id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteUser(String name) {
        String sql = "delete from user where name = ?";
        jdbcTemplate.update(sql, name);
    }

    public boolean isUserNotExist(String name) {
        String readSql = "select * from user where name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }
}
