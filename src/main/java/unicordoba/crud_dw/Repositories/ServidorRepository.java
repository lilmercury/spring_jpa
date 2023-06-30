package unicordoba.crud_dw.Repositories;
import unicordoba.crud_dw.Entity.servidor;

import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ServidorRepository extends CrudRepository<servidor, Integer>, JpaSpecificationExecutor<servidor>{
    
}
