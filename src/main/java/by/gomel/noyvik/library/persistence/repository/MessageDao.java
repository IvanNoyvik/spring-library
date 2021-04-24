package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Long> {



}
