package arki.springboot.elasticsearch.espractice;

import arki.springboot.elasticsearch.espractice.Utility.ESUtility;
import arki.springboot.elasticsearch.espractice.Utility.URLUtility;
import arki.springboot.elasticsearch.espractice.pojo.Job;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
class EsPracticeApplicationTests {

	@Autowired
	URLUtility urlUtility;

	@Autowired
	ESUtility esUtility;

	@Test
	void contextLoads() throws IOException {
		//System.out.println(urlUtility.getDoc().toString());
//		urlUtility.parseCookie();
//		urlUtility.spider2("3742637833");
//		urlUtility.monster();
//		esUtility.store();
	}

	@Test
	void test() throws IOException {
//		ArrayList list = (ArrayList) esUtility.searchbypage("software developer", 0, 10);
//		System.out.println(list.size());
//		System.out.println("```````````````");
	}

}
