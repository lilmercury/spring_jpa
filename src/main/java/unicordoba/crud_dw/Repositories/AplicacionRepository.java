package unicordoba.crud_dw.Repositories;
import unicordoba.crud_dw.Entity.aplicacion;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AplicacionRepository extends CrudRepository<aplicacion, Integer>, JpaSpecificationExecutor<aplicacion>{
    
}
