package spring.data.elasticsearch;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import spring.data.elasticsearch.bean.Test2;
import spring.data.elasticsearch.bean.User;

public class IndexTest extends BaseTest {

	@Test
	public void index() {
		User user = new User();
		user.setId(getId());
		user.setName(getName());
		user.setAge(getAge());
		user.setAddress(getAddress());
		user.setCreatedAt(getCreatedAt());
		userRepository.index(user);
	}
	
	@Test
	public void test2Insert() {
		
		for(int i=0; i<1000; i++){
			
			Test2 test2 = new Test2();
			test2.setColumn1(RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz1234567890"));
			test2.setColumn2(RandomStringUtils.randomAlphabetic(10));
			test2.setColumn3(RandomStringUtils.randomAlphanumeric(10));
			test2.setColumn4(RandomStringUtils.randomAscii(10));
			test2.setColumn5(RandomStringUtils.randomNumeric(10));
			
			test2Repository.index(test2);
			
			if(i%100 == 0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void test2Query(){
		
		String column1 = "tuo";
		List<Test2> col1List = test2Repository.findByColumn1(column1);
		System.out.println(col1List.size());
		
		col1List = test2Repository.findByColumn1Like(column1);
		System.out.println(col1List.size());
		
		
	}
	@Test
	public void test2Page(){
		
		String column1 = "tuo";
		Pageable pageable = new PageRequest(0, 5, Sort.Direction.DESC, "column1");
		Page<Test2> page = test2Repository.findBycolumn1Like(column1, pageable);
		
		for(Test2 t : page){
			System.out.println(t.getColumn1());
		}
	}
	
//	@Test
//	public void test2Builder(){
//		XContentBuilder builder = XContentFactory.jsonBuilder();
//		builder.field("column1", "tuo");
//		QueryBuilder query = new
//	}
	
}