package com.mergen.task3;

import com.mergen.task3.entity.Address;
import com.mergen.task3.entity.User;
import com.mergen.task3.repository.UserRepository;
import jakarta.persistence.criteria.Join;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Task3ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenSearchingByAddressStateAndUserFullName_thenOneUserIsReturned(){
		Specification<User> users = hasNameLike("John Doe")
				.and(hasStateLike("CA"));
		List<User> johnDoes = userRepository.findAll(users);
		assertThat(johnDoes).hasSize(1);
	}

	@Test void whenSearchingByAddressCity_thenUserListIsReturned() {
		List<User> users = userRepository.findByCity("San Francisco");
		assertThat(users).hasSize(2);
	}


	public static Specification<User> hasNameLike(String name){
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.like(root.get("name"), name);
	}

	public static Specification<User> hasStateLike(String state){
		return (root, query, criteriaBuilder) -> {
			Join<Address, User> userAddressJoin = root.join("address");
			return criteriaBuilder.equal(userAddressJoin.get("state"), state);
        };
	}
}
