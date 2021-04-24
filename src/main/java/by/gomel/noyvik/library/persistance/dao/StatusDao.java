package by.gomel.noyvik.library.persistance.dao;

import by.gomel.noyvik.library.model.Status;

public interface StatusDao extends CrudDao<Status> {

    Status getStatus(String statusStr);

    Status getOkStatus();

    Status getLimitedStatus();

    Status getLockedStatus();

}
