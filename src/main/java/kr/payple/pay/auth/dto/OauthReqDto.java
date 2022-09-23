package kr.payple.pay.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.payple.pay.auth.entity.PartnerServiceEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OauthReqDto {
    private String service_id;
    private String service_key;
    private String group_id;
    private String conDate;

    @Builder
    public OauthReqDto(String service_id, String service_key, String group_id, String conDate) {
        this.service_id = service_id;
        this.service_key = service_key;
        this.group_id = group_id;
        this.conDate = conDate;
    }

    //dto -> entity
    public PartnerServiceEntity toEntity(){
        PartnerServiceEntity ps = PartnerServiceEntity.builder()
                .serviceId(service_id)
                .service_key(service_key)
                .build();
        return ps;
    }

    public String getServiceID(){
        return this.service_id;
    }

}
