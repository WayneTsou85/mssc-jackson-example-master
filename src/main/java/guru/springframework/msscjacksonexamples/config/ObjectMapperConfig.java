package guru.springframework.msscjacksonexamples.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.LogicalType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .withCoercionConfig(LogicalType.Boolean, b -> b.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail))
                .withCoercionConfig(LogicalType.Boolean, b -> b.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail))
                .build();
    }
}
