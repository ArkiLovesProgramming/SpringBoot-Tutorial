package arki.springboot.sb05druid2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RestController
public class TestDruid {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/add/{name}")
    public String addUser(@PathVariable String name){
        String sql = "insert into users(`name`) values(?)";
        Object[] args = new Object[1];
        args[0] = name;
        jdbcTemplate.update(sql, args);
        return "successful";
    }

    @RequestMapping("/query")
    public List queryUsers(){
        String sql = "select * from users";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users;
    }
}
