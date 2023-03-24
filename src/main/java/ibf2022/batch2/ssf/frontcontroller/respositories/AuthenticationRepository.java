package ibf2022.batch2.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

	@Autowired @Qualifier("disable")
	private RedisTemplate<String, String> template;
	
	public void disableUser(String username){
		String key = username;
		String value = "true";
		template.opsForValue().set(key, value,Duration.ofMinutes(30));
		}
		
	public String checkDisable(String username){
		return template.opsForValue().get(username);
	}
}
