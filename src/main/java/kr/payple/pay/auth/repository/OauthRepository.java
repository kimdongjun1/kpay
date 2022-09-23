package kr.payple.pay.auth.repository;

import kr.payple.pay.auth.entity.PartnerServiceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OauthRepository extends JpaRepository<PartnerServiceEntity, Integer> {
    @EntityGraph(attributePaths = {"partner_group"})
    List<PartnerServiceEntity> findByServiceId(@Param("service_id") String service_id);

}
