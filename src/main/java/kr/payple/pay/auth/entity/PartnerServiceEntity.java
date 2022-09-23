package kr.payple.pay.auth.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장된다.
@Entity(name = "partner_service")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PartnerServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_no;

    private Integer group_no;

    @Column(name = "service_id")
    private String serviceId;

    private String service_key;

    @CreatedDate
    @Transient
    private String conDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_no",insertable = false, updatable = false)
    private PartnerGroupEntity partner_group;

    // Java 디자인 패턴, 생성 시점에 값을 채워줌
    @Builder
    public PartnerServiceEntity(Integer service_no, Integer group_no, String serviceId, String service_key, PartnerGroupEntity partner_group, String conDate){
        this.service_no = service_no;
        this.group_no = group_no;
        this.serviceId = serviceId;
        this.service_key = service_key;
        this.partner_group = partner_group;
        this.conDate = conDate;
    }

}
