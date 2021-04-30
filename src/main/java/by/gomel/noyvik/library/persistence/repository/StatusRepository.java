package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Status findByStatus(String status);

//    Status getOkStatus();

//    Status getLimitedStatus();

//    Status getLockedStatus();

}
