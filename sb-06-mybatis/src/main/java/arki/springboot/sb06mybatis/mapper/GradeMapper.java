package arki.springboot.sb06mybatis.mapper;

import arki.springboot.sb06mybatis.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper : 表示本类是一个 MyBatis 的 Mapper，告诉 springboot，如果不添加的话，@Repository并不会把该接口创建成 bean，因为它是接口。
//也可以在启动类上添加@MapperScan("...")，这样就不用在每个接口上添加@Mapper 了

//@Repository
@Mapper
public interface GradeMapper {
    Grade getGrade(@Param("id") int Id);

    List<Grade> getGrades();

}
