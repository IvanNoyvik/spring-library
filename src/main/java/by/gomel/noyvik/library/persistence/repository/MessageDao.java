package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageDao extends CrudRepository<Message, Long> {



}
