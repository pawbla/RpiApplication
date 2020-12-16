package dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Role;
import dao.entities.Users;

@Repository
@Transactional
public interface ManageUsersDao extends JpaRepository<Users, Long> {

	
	public Users findByUsername (final String username);
	
	@Query("FROM Users WHERE user_id=:user_id")
	public Users findByUserId(final int user_id);
	
	@Query("FROM Users")
	public List<Users> findUsers();
	
	@Query("FROM Role WHERE role=:role")
	public Role findRole(final String role);
	
	@Query("SELECT count(*) FROM Users WHERE role_id = (SELECT role_id FROM Role WHERE role='ROLE_ADMIN')")
	public int countAdmins();
	
	@Modifying
	@Transactional
	@Query("UPDATE Users SET password = :password WHERE user_id = :user_id")
	public void updatePassword(final int user_id, String password);
}
