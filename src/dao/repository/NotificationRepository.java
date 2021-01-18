package dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Notification;
import dao.entities.NotificationEntity;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
	NotificationEntity findById(int id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Notification WHERE user_id=:user_id AND notification_id=:notification_id")
	public void deleteByUserIdAndNotificationId(final int user_id, final int notification_id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM NotificationEntity WHERE id=:id")
	public void deleteNotificationEntityById(final int id);
	
	@Query("FROM Notification WHERE user_id = :user_id")
	public List<Notification> findNotificationsByUserId(int user_id);
}
