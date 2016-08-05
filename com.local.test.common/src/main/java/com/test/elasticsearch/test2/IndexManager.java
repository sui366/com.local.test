//package com.test.elasticsearch.test2;
//
//import javax.swing.text.AbstractDocument;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.QueryBuilders;
//
//public class IndexManager {
//
//	private static Object lock = new Object();
//
//	private static TransportClient client;
//
//	private static Log LOG = LogFactory.getLog(IndexManager.class);
//
//	/**
//	 * @return
//	 */
//	public static TransportClient getClient() {
//		if (client == null) {
//			synchronized (lock) {
//				Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", ConfigObject.getInstance().getClusterName()).build();
//				client = new TransportClient(settings);
//				for (ConfigObject.ESNodeObject nodeObject : ConfigObject.getInstance().getEsNodeObjects()) {
//					client.addTransportAddress(new InetSocketTransportAddress(nodeObject.getAddress(), nodeObject.getPort()));
//				}
//			}
//		}
//		return client;
//	}
//
//	/**
//	 * 保存数据 判断文档信息是否存在，如不存在，直接写入
//	 * 如果存在，判断当前文档信息的contentMD5于索引内的MD5是否一致，如不一致重新写入，如一致，直接返回
//	 * 
//	 * @param source
//	 * @return 返回id
//	 */
//	public static String saveIndexData(AbstractDocument document) {
//		if (document == null) {
//			throw new IllegalArgumentException();
//		}
//
//		String contentMD5 = MD5Util.MD5(document.getContent());
//
//		SearchResponse searchResponse = getClient().prepareSearch(Constants.ES_INDEX).setTypes(Constants.ES_DATAINFO_TYPE).setQuery(QueryBuilders.termQuery("url", document.getUrl())).execute().actionGet();
//		if (searchResponse == null || searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() <= 0) {
//			// id不存在，写入新数据
//			document.setId(IdBuilder.uuid());
//			document.setContentMD5(contentMD5);
//			document.setNewUpdatable(YN.Yes);
//			document.setInsertTime(new Date());
//			document.setUpdateTime(new Date());
//			getClient().prepareIndex(Constants.ES_INDEX, Constants.ES_DATAINFO_TYPE).setId(document.getId()).setSource(document.toString()).execute().actionGet();
//			if (LOG.isInfoEnabled()) {
//				LOG.info("Create Index " + document.getId() + " ok & The url is " + document.getUrl());
//			}
//			return document.getId();
//		} else {
//			// 有可能更新数据
//			SearchHit searchHit = searchResponse.getHits().getAt(0);
//			String oldContentMD5 = (String) searchHit.getSource().get("contentMD5");
//			if (oldContentMD5 == null || oldContentMD5.equals("") || !oldContentMD5.equals(contentMD5)) {
//				// 清除掉相同url的数据
//				String id = IdBuilder.uuid();
//				getClient().prepareDeleteByQuery(Constants.ES_INDEX).setTypes(Constants.ES_DATAINFO_TYPE).setQuery(QueryBuilders.termQuery("url", document.getUrl())).execute().actionGet();
//				document.setId(id);
//				getClient().prepareIndex(Constants.ES_INDEX, Constants.ES_DATAINFO_TYPE).setId(id).setSource(document.toString()).execute().actionGet();
//				if (LOG.isInfoEnabled()) {
//					LOG.info("ReCreate Index " + id + " ok & The url is " + document.getUrl());
//				}
//				return id;
//			}
//			if (LOG.isInfoEnabled()) {
//				LOG.info("Exist Index " + searchResponse.getHits().getAt(0).getId() + " ok & The url is " + document.getUrl());
//			}
//			return searchResponse.getHits().getAt(0).getId();
//		}
//	}
//
//	/**
//     * 清除ES_INDEX下的所有数据 
//     */
//    public static void clearAllDatas() {
//        getClient().prepareDeleteByQuery(Constants.ES_INDEX).setTypes(Constants.ES_DATAINFO_TYPE).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
//    }