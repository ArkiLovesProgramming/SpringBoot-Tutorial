package arki.springboot.elasticsearch.espractice.controller;


import arki.springboot.elasticsearch.espractice.Utility.ESUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class ResultController {

    @Autowired
    ESUtility esUtility = new ESUtility();

    @ResponseBody
    @RequestMapping("/search/{keyword}/{pageindex}/{pagesize}")
    public ArrayList<Map> search(@PathVariable String keyword,
                                 @PathVariable int pageindex,
                                 @PathVariable int pagesize) throws IOException {
        return esUtility.searchbypage(keyword, pageindex, pagesize);
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
