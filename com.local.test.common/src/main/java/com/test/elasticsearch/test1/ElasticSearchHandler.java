package com.test.elasticsearch.test1;

import java.beans.PropertyDescriptor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticSearchHandler {

	private Client client;

	public ElasticSearchHandler() {
		// 使用本机做为节点
		this("127.0.0.1");
	}

	public ElasticSearchHandler(String ipAddress) {

		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").put("client.transport.sniff", true).build();

		try {
			client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 建立索引,索引建立好之后,会在elasticsearch-0.20.6\data\elasticsearch\nodes\0创建所以你看
	 * 
	 * @param indexName
	 *            为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * @param indexType
	 *            Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
	 * @param jsondata
	 *            json格式的数据集合
	 * 
	 * @return
	 */
	public void createIndexResponse(String indexname, String type, List<String> jsondata) {
		IndexRequestBuilder requestBuilder = client.prepareIndex(indexname, type).setRefresh(true);
		for (int i = 0; i < jsondata.size(); i++) {
			requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
		}

	}

	/**
	 * 创建索引
	 * 
	 * @param client
	 * @param jsondata
	 * @return
	 */
	public IndexResponse createIndexResponse(String indexname, String type, String jsondata) {
		IndexResponse response = client.prepareIndex(indexname, type).setSource(jsondata).execute().actionGet();
		return response;
	}

	/**
	 * 执行搜索
	 * 
	 * @param queryBuilder
	 * @param indexname
	 * @param type
	 * @return
	 */
	public List<Object> searcher(QueryBuilder queryBuilder, String indexname, String type, Class<?> paramClass) throws IllegalAccessException, InstantiationException {

		List<Object> list = new ArrayList<Object>();
		SearchResponse searchResponse = client.prepareSearch(indexname).setTypes(type).setQuery(queryBuilder).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数=" + hits.getTotalHits());
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {

				Object object = paramClass.newInstance();
				PropertyDescriptor[] property = PropertyUtils.getPropertyDescriptors(object);

				for (int i = 0; i < property.length; i++) {
					String propertyName = property[i].getName();

					if (PropertyUtils.isWriteable(object, propertyName)) {
						try {
							Object propertyValue = hit.getSource().get(propertyName);
							BeanUtils.setProperty(object, propertyName, propertyValue);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				list.add(object);
			}
		}
		return list;
	}

	/**
	 * 分页搜索
	 */
	public List<Object> searcher(QueryBuilder queryBuilder, String indexname, String type, Class<?> paramClass, int from, int pageSize) throws IllegalAccessException, InstantiationException {

		List<Object> list = new ArrayList<Object>();
		SearchResponse searchResponse = client.prepareSearch(indexname).setTypes(type).setFrom(from).setSize(pageSize).setQuery(queryBuilder).addSort("id", SortOrder.DESC).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数=" + hits.getTotalHits());
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {

				Object object = paramClass.newInstance();
				PropertyDescriptor[] property = PropertyUtils.getPropertyDescriptors(object);

				for (int i = 0; i < property.length; i++) {
					String propertyName = property[i].getName();

					if (PropertyUtils.isWriteable(object, propertyName)) {
						try {
							Object propertyValue = hit.getSource().get(propertyName);
							BeanUtils.setProperty(object, propertyName, propertyValue);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				list.add(object);
			}
		}

		// SearchResponse actionGet =
		// client.prepareSearch("indexName").setTypes("typeName").setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("attr1",
		// "value1"))).setFrom(100).setSize(200).execute().actionGet();

//		SearchResponse actionGet = client.prepareSearch("indexName").setTypes("typeName").setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("attr1", "value1"))).setScroll(new TimeValue(60000)).setSize(200).execute().actionGet();
//		String scrollId = actionGet.getScrollId();
//		SearchResponse actionGet = elasticClient.prepareSearchScroll(actionGet.getScrollId())
//				.setScroll(new TimeValue(600000))
//				.execute().actionGet();

		return list;

	}

	/**
	 * 删除索引
	 */
	public void deleteIndex(String index, String type, String id) {
		DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
	}

	/**
	 * 删除索引
	 */
	public void deleteIndex(String index, String type, QueryBuilder queryBuilder) {
		List<Object> list = new ArrayList<Object>();
		SearchResponse searchResponse = client.prepareSearch(index).setTypes(type).setQuery(queryBuilder).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {
				deleteIndex(index, type, hit.getId());
			}
		}
	}

	public static void main(String[] args) {
		ElasticSearchHandler esHandler = new ElasticSearchHandler();
		List<String> jsondata = DataFactory.getInitJsonData();
		String indexname = "indexdemo";
		String type = "typedemo";

		// 添加
		// esHandler.createIndexResponse(indexname, type, jsondata);

		// 查询条件
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "颗粒");

		QueryBuilders.rangeQuery("attr1").gt("value1");// gt lt eq gte lte;
		// FilterBuilders.orFilter(FilterBuilders.termFilter("attr1",
		// "value1"),FilterBuilders.termFilter("attr2", "value2"));
		// FilterBuilders.andFilter(FilterBuilders.termFilter("attr1",
		// "value1"),FilterBuilders.termFilter("attr2", "value2"));

		// 删除
		// esHandler.deleteIndex(indexname, type, queryBuilder);

		List<Object> result = null;
		try {
			result = esHandler.searcher(queryBuilder, indexname, type, Medicine.class, 2, 5);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < result.size(); i++) {
			Medicine medicine = (Medicine) result.get(i);
			System.out.println("(" + medicine.getId() + ")药品名称:" + medicine.getName() + "\t\t" + medicine.getFunction());
		}
	}
}