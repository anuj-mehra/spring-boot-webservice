package com.anuj.springrestservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service("userDaoService")
public class UserDaoService {

	private static final List<UserDTO> users = new ArrayList<>();

	static{
		final Date d = new Date(); 
		users.add(new UserDTO(1,"Adam", d));
		users.add(new UserDTO(2,"Eve", d));
		users.add(new UserDTO(3,"Jack", d));
	}

	public List<UserDTO> findAll(){
		return users;
	}

	public UserDTO save(UserRequest userRequest){
		
		final UserDTO maxID = users.stream().max((UserDTO t1, UserDTO t2) -> t1.getId().compareTo(t2.getId())).get();
		
		final UserDTO user = new UserDTO(maxID.getId() + 1, userRequest.getName(), userRequest.getDob());
		users.add(user);
		return user;
	}

	public Optional<UserDTO> findOne(int id){
		final Optional<UserDTO> user = users.stream().filter(u -> u.getId()==id).findFirst();
		return user;
	}
	
	public boolean deleteUserById(int id){
		final Optional<UserDTO> user = users.parallelStream().filter(u -> u.getId() == id).findFirst();
		
		boolean removed = false;
		if(user.isPresent()){
			users.remove(user.get());
			removed = true;
		}
		
		return removed;
	}
	
}
