package unicordoba.crud_dw.Repositories;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.*;

import unicordoba.crud_dw.Entity.servidor;
import unicordoba.crud_dw.Entity.servidor_;
import unicordoba.crud_dw.Entity.aplicacion;
import unicordoba.crud_dw.Entity.aplicacion_;

public class ServSpecs {
    
    public static Specification<servidor> getCountserverByApp() {
        //PARA OBTENER LOS SERVIDORES QUE TIENEN MAS DE 1 APLICACIÓN ASOCIADA
        return new Specification<servidor>() {

            @Override
            public Predicate toPredicate(Root<servidor> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                Join<aplicacion, servidor> datos = root.join("applications", JoinType.INNER);
                Predicate appIdPredicate = criteriaBuilder.equal(datos.get("id"), root.get("applications").get("id"));
                        
                query.multiselect(root.get("nombre"), root.get("id"));
                query.groupBy(root.get(servidor_.ID), root.get(servidor_.NOMBRE));
                query.having(criteriaBuilder.gt(criteriaBuilder.count(datos), 1));
                
                return appIdPredicate;
            }

        };
    }
}
