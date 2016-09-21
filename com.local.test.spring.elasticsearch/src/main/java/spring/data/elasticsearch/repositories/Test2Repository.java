package spring.data.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.data.elasticsearch.bean.Test2;

public interface Test2Repository extends ElasticsearchRepository<Test2, String> {

	/**
	 * 按字段查询
	 */
	List<Test2> findByColumn1(String column1);
	
	/**
	 * 模糊查询
	 */
	List<Test2> findByColumn1Like(String column1);
	
	/**
	 * 分页&排序
	 */
	Page<Test2> findBycolumn1Like(String column1, Pageable pageable);
	
//	boolean exists(String column1);
//	
//	long count(Predicate predicate);
}






