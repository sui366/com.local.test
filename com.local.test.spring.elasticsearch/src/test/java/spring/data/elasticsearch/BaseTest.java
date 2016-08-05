package spring.data.elasticsearch;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import spring.data.elasticsearch.repositories.UserRepository;

@ContextConfiguration(locations = "classpath:spring.xml")
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	protected ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	protected UserRepository userRepository;

	protected String getId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	protected String getName() {
		return RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz1234567890");
	}

	protected int getAge() {
		return RandomUtils.nextInt(10, 100);
	}

	protected String getAddress() {
		final String[] addrs = { "shanghai", "beijing", "guangzhou", "shenzhen", "hunan", "henan", "xinjiang",
				"haerbing", "jinan", "nanjing" };
		return addrs[RandomUtils.nextInt(0, addrs.length - 1)];
	}

	protected Date getCreatedAt() {
		return DateUtils.addDays(new Date(), RandomUtils.nextInt(10, 100));
	}
}
