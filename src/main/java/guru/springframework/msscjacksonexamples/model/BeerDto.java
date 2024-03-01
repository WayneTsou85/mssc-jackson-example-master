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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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

    public static void main(String[] args) {
        String day = "2024-02-30 12:00:23";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDate parse = LocalDate.parse(day, dateTimeFormatter);
        System.out.println(LocalDateTime.parse(day, dateTimeFormatter));
        System.out.println(MonthDay.now());
        System.out.println(YearMonth.now());
        System.out.println(parse);
    }
}
