package unicordoba.crud_dw.Repositories;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.util.List;

import unicordoba.crud_dw.Entity.aplicacion;
import unicordoba.crud_dw.Entity.aplicacion_;
import unicordoba.crud_dw.Entity.servidor;
import unicordoba.crud_dw.Entity.servidor_;

public class AppSpecs {
    //PARA OBTENER una aplicacion filtrando por su nombre
    public static Specification<aplicacion> getAppByname(String name) {

        return new Specification<aplicacion>() {

            @Override
            public Predicate toPredicate(Root<aplicacion> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                Predicate appNombre = criteriaBuilder.equal(root.get(aplicacion_.nombre), name);
                return appNombre;
            }

        };
    }

    public static Specification<aplicacion> getAppBySOWithServer(String name) {
        //PARA OBTENER LAS aplicaciones DE UN SERVIDOR EN PARTICULAR  FILTRADO POR SU Sistema operativo
        return new Specification<aplicacion>() {

            @Override
            public Predicate toPredicate(Root<aplicacion> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                Join<aplicacion, servidor> datos = root.join("server", JoinType.INNER);

                Predicate servidorIdPredicate = criteriaBuilder.equal(datos.get("id"), root.get("server").get("id"));
                Predicate SOnombre = criteriaBuilder.equal(datos.get(servidor_.SISTEMA_OPERATIVO), name);

                query.multiselect(root.get("nombre"), root.get("id"), root.get("server"));
                
                Predicate finalPredicate = criteriaBuilder.and(servidorIdPredicate, SOnombre);
                query.where(finalPredicate);

                return finalPredicate;

            }

        };
    }
}
