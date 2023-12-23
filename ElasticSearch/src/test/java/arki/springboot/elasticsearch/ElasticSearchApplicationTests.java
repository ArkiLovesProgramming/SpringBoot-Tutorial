package arki.springboot.elasticsearch;

import arki.springboot.elasticsearch.pojo.User;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexAction;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElasticSearchApplicationTests {

	final static String INDEX = "arki_springboot";

	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	@Test
	void contextLoads() {
		User user = new User("狂神说", 28);
		System.out.println();
		System.out.println(JSON.toJSONString(user));
	}

	@Test
	void testCreateIndex() throws IOException {
		CreateIndexRequest request = new CreateIndexRequest(INDEX);
		request.mapping("{\n" +
				" \"properties\":{\n" +
				"  \"name\":{\n" +
				"   \"type\":\"text\"\n" +
				"  }\n" +
				" }\n" +
				"}", XContentType.JSON);
		CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(response);
	}


	// 测试索引是否存在
	@Test
	void testExistsIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest();
		GetRequest request3 = new GetRequest(INDEX);
		IndexRequest request2 = new IndexRequest(INDEX);
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	@Test
	// 删除索引
	public void deleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
		//判断索引是否存在
		AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
		//返回true代表删除
		System.out.println(((AcknowledgedResponse) delete).isAcknowledged());
	}

	// 添加文档
	@Test
	void testAddDocument() throws IOException {
		User user = new User("狂神说", 28);
		IndexRequest request = new IndexRequest(INDEX);
		// 规则 PUT /index/_doc/1
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));
		// 将数据放入请求 json
		request.source(JSON.toJSONString(user), XContentType.JSON);
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		System.out.println(JSON.toJSONString(user));
		System.out.println(response.toString());
		System.out.println(response.status());
	}

	@Test
	//获取文档，判断是否存在 GET qiandu_index/1
	public void existsDocument() throws IOException {
		GetRequest request = new GetRequest(INDEX, "1");
		//不获取返回的_source的上下文
		request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
		boolean exists = client.exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	@Test
	//获取文档的信息
	public void getDocument() throws IOException {
		GetRequest request = new GetRequest(INDEX, "1");
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		//打印内容 或者用map
		System.out.println(response.getSourceAsString());
		//返回全部内容
		System.out.println(response);
	}

	// 更新文档
	@Test
	void testUpdateDocument() throws IOException {
		UpdateRequest request = new UpdateRequest(INDEX, "1");
		request.timeout("1s");
		User user = new User("arki", 18);
		request.doc(JSON.toJSONString(user), XContentType.JSON);
		UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
		System.out.println(updateResponse);
	}

	@Test
	//删除文档
	public void deleteDocument() throws IOException {
		DeleteRequest request = new DeleteRequest(INDEX, "1");
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
	}

	// 批量插入数据（修改，删除类似操作）
	@Test
	void testBulkRequest() throws IOException {
		BulkRequest request = new BulkRequest();
		request.timeout("10s");

		ArrayList<User> users = new ArrayList<>();
		users.add(new User("kuangshen1", 21));
		users.add(new User("kuangshen2", 22));
		users.add(new User("kuangshen3", 23));
		users.add(new User("xiaofan1", 18));
		users.add(new User("xiaofan2", 19));

		// 批处理请求， 修改，删除，只要在这里修改相应的请求就可以
		for (int i = 0; i < users.size(); i++) {
			request.add(new IndexRequest(INDEX)
					.id(String.valueOf(i + 1))
					.source(JSON.toJSONString(users.get(i)), XContentType.JSON));
		}

		BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
		//是否失败，返回false表示成功
		System.out.println(bulkResponse.hasFailures());
	}

	// 查询文档
	@Test
	void testSearch() throws IOException {
		SearchRequest searchRequest = new SearchRequest(INDEX);
		// 构建搜索条件
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		// 查询条件， 可以使用QueryBuilders工具类实现
		// QueryBuilders.termQuery 精确
		// QueryBuilders.matchLLQuery() 匹配所有
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "kuangshen1");
		// MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
		sourceBuilder.query(termQueryBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		searchRequest.source(sourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println(JSON.toJSON(searchResponse.getHits()));
		System.out.println("======================================");
		for (SearchHit documentFields : searchResponse.getHits().getHits()) {
			System.out.println(documentFields.getSourceAsMap());
		}

	}

}
