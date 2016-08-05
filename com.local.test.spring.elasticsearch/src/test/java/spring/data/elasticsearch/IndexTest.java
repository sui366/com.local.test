package spring.data.elasticsearch;

import org.junit.Test;

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
}