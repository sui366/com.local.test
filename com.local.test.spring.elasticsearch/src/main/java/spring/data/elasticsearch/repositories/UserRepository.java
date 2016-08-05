package spring.data.elasticsearch.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.data.elasticsearch.bean.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {

}
