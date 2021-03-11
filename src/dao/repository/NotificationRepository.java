package dao.repository;

import dao.entities.Notification;
import dao.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
    @Query("SELECT n FROM NotificationEntity n")
    List<NotificationEntity> findAllNotificationEntities();

    @Transactional
    @Modifying
    @Query("UPDATE Notification SET read = :status WHERE id = :id")
    public void updateReadStatus(boolean status, int id);
}
