package com.flowsoft.wanda;

import java.util.List;

import com.flowsoft.domain.WandaUser;

public interface WandaUserDao {

	WandaUser createUser(String username, String password, String firstname,
			String lastname);

	WandaUser createUser(WandaUser w);

	List<WandaUser> findAllUser();

	WandaUser findUserByName(String name);

	Boolean exist(String username);

}
