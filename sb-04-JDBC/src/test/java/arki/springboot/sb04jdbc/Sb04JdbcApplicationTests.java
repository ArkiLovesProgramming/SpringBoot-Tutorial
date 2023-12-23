package arki.springboot.sb04jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Sb04JdbcApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        String sql = "select * from student where StudentNo = ?";
        Object[] args = new Object[1];
        args[0] = 1000;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, args);
        System.out.println(maps);
        //关闭连接
        connection.close();
    }

}
