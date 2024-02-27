package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;

    @NotBlank
    private String beerName;

    @NotBlank
    private String beerStyle;

    @Positive
    private Long upc;

    private Boolean b;
    private Map<String, Object> map;
    private AAA aaa;

    private BigDecimal price;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdatedDate;

    @Data
    public static class AAA {
        @JsonProperty("s_S")
        private String s;
        private boolean b;
        private int i;
    }
}
