package security.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flowsoft.domain.WandaUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context.xml" })
public class SpringSecurityTest {

	@Test
	public void annotationTest() {

		WandaUser admin = new WandaUser("testAdmin", "admin", true,
				"ROLE_ADMIN", "testAdmin@admin.com");
		WandaUser user = new WandaUser("testUser", "user", true, "ROLE_USER",
				"testUser@user.com");

		List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl(admin.getRole()));

		List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl(user.getRole()));

	}
}
