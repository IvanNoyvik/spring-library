package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    @Query("from Message m left join fetch m.user u join fetch u.authenticate left join fetch u.status")
    List<Message> findAllWithUserLink();

    void removeAllByUserId(Long userId);

    List<Message> findAllByUserId(Long userId);


}
