package arki.springboot.elasticsearch.espractice.Utility;

import arki.springboot.elasticsearch.espractice.pojo.Job;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ESUtility {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    private static final String INDEX = "jobs";

    @Autowired
    URLUtility urlUtility;

    public void store() throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("100s");

        ArrayList<Job> jobs = new ArrayList<>();
        jobs = urlUtility.monster();

        // 批处理请求， 修改，删除，只要在这里修改相应的请求就可以
        for (int i = 0; i < jobs.size(); i++) {
            request.add(new IndexRequest(INDEX)
                    .id(jobs.get(i).getName())
                    .source(JSON.toJSONString(jobs.get(i)), XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        //是否失败，返回false表示成功
        System.out.println(bulkResponse.hasFailures());
    }


    public ArrayList<Map> searchbypage(String keyword, int pageIndex, int pageSize) throws IOException {
        ArrayList<Map> list = new ArrayList<>();
        if (pageIndex < 0){
            pageIndex = 0;
        }
        SearchRequest jobsearch = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder matchQueryBuilder = new MultiMatchQueryBuilder(keyword, "name", "description", "location", "company");
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(pageIndex).size(pageSize);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").field("description").field("location").field("company");
        highlightBuilder.preTags("<span class=\"highlight\">");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        jobsearch.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(jobsearch, RequestOptions.DEFAULT);
        System.out.println("``````````````");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            Map<String, Object> map = documentFields.getSourceAsMap();
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField name = highlightFields.get("name");
            if (name != null) {
                Text[] fragments = name.fragments();
                StringBuilder new_name = new StringBuilder();
                for (Text text : fragments) {
                    new_name.append(text);
                }
                map.put("name", new_name.toString());
            }
            HighlightField company = highlightFields.get("company");
            if (company != null) {
                Text[] fragments = company.fragments();
                StringBuilder new_company = new StringBuilder();
                for (Text text : fragments) {
                    new_company.append(text);
                }
                map.put("company", new_company.toString());
            }
            HighlightField location = highlightFields.get("location");
            if (location != null) {
                Text[] fragments = location.fragments();
                StringBuilder new_location = new StringBuilder();
                for (Text text : fragments) {
                    new_location.append(text);
                }
                map.put("location", new_location.toString());
            }
            HighlightField description = highlightFields.get("description");
            if (description != null) {
                Text[] fragments = description.fragments();
                StringBuilder new_description = new StringBuilder();
                for (Text text : fragments) {
                    new_description.append(text);
                }
                map.put("description", new_description.toString());
            }
            list.add(map);
        }
        return list;
    }


}
