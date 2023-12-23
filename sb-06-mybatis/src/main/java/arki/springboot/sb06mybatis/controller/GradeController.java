package arki.springboot.sb06mybatis.controller;

import arki.springboot.sb06mybatis.mapper.GradeMapper;
import arki.springboot.sb06mybatis.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GradeController {

    @Autowired
    private GradeMapper gradeMapper;

    @GetMapping("/grade/{id}")
    public String getGrade(@PathVariable int id){
        return gradeMapper.getGrade(id).toString();
    }

    @GetMapping("/grades")
    public List<Grade> getGrades(){
        return gradeMapper.getGrades();
    }

}
