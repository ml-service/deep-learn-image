package com.elasticsearchwork;



import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ElasticsearchClientConfiguration {
    				
		
		//The config parameters for the connection
		private static final String HOST = "localhost";
		private static final int PORT_ONE = 9200;
		private static final int PORT_TWO = 9201;
		private static final String SCHEME = "http";
		 
		private static RestHighLevelClient restHighLevelClient;
		//private static ObjectMapper objectMapper = new ObjectMapper();
		 
		private static final String INDEX = "learnimage";
		private static final String TYPE = "bill";		
		
		/**
		 * Implemented Singleton pattern here
		 * so that there is just one connection at a time.
		 * @return RestHighLevelClient
		 */				
		private static synchronized RestHighLevelClient makeConnection() {
		 
		    if(restHighLevelClient == null) {
		        restHighLevelClient = new RestHighLevelClient(
		                RestClient.builder(
		                        new HttpHost(HOST, PORT_ONE, SCHEME),
		                        new HttpHost(HOST, PORT_TWO, SCHEME)));
		    }
		 
		    return restHighLevelClient;
		}
		
	    private static synchronized void closeConnection() throws IOException {
	        restHighLevelClient.close();
	        restHighLevelClient = null;
	    }
	    
	    private static ImageDataItem insertLineItem(ImageDataItem LineItem){
	    	LineItem.setLineId(UUID.randomUUID().toString());
	        
	    	Map<String, Object> dataMap = new HashMap<String, Object>();
	        
	        //dataMap.put("LineId", LineItem.getLineId());
	        //dataMap.put("LineItem", LineItem.getLineItem());
	        			        
	        @SuppressWarnings("deprecation")
			IndexRequest indexRequest = new IndexRequest (INDEX, TYPE, LineItem.getLineId())
	                					.source(dataMap);
	        
	        try {	            
				@SuppressWarnings("unused")
				IndexResponse response = restHighLevelClient.index(indexRequest, null);
	        } catch(ElasticsearchException e) {
	            e.getDetailedMessage();
	        } catch (java.io.IOException ex){
	            ex.getLocalizedMessage();
	        }
	        return LineItem;
	    }
		
		
		public static void main(String LineItem) throws IOException {
			 
			//makeConnection();		
			
			System.out.println(LineItem);
			ImageDataItem ImageDataItem = new ImageDataItem();
			ImageDataItem.setLineItem(LineItem);	        
			ImageDataItem = insertLineItem(ImageDataItem);
	        System.out.println("Person inserted --> " + ImageDataItem);
			
			//closeConnection();
			 
		 }
		
		
}
