package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("from Role r left join fetch r.users where r.role = :role")
    Role findByRole(@Param("role") String role);



}
