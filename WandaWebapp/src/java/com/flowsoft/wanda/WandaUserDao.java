package com.flowsoft.wanda;

import java.util.List;

import com.flowsoft.entities.WandaUser;

public interface WandaUserDao {

	public WandaUser createUser(String username, String password,
			String firstname, String lastname);

	public WandaUser createUser(WandaUser w);

	public List<WandaUser> findAllUser();

	public WandaUser findUserByName(String name);

	public Boolean exist(String username);

}
