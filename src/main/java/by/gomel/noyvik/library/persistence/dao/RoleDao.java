package by.gomel.noyvik.library.persistence.dao;

import by.gomel.noyvik.library.model.Role;

public interface RoleDao extends CrudDao<Role> {

    Role getAdminRole();

    Role getUserRole();


}
