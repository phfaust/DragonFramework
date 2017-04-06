package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//Auto magic created queries && no need to add annotations: see slides! 
	public List<User> findByName(String s);
	public List<User> findAll();
	
	
}
