//package com.test.rest;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//public class RestTemplateExample {
//
//	public static void main(String[] args) { 
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		requestFactory.setConnectTimeout(1000); 
//		requestFactory.setReadTimeout(1000);
//
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("X-Auth-Token", "e348bc22-5efa-4299-9142-529f07a18ac9");
//
//        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
//        postParameters.add("owner", "11");
//        postParameters.add("subdomain", "aoa");
//        postParameters.add("comment", "");
//
//        HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);
//
//        ParseResultVo exchange = null;
//        try {
//            exchange = restTemplate.postForObject("http://l-dnsutil1.ops.beta.cn6.qunar.com:10085/v1/cnames/tts.piao",  requestEntity, ParseResultVo.class);
//            logger.info(exchange.toString());
//        } catch (RestClientException e) {
//            logger.info("。。。。");
//        }
//	}
//}
