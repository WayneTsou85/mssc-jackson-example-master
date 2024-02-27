package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoTest extends BaseTest {
    ObjectMapper objectMapper = JsonMapper.builder()
            .withCoercionConfig(LogicalType.Boolean, b -> b.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail))
            .withCoercionConfig(LogicalType.Boolean, b -> b.setCoercion(CoercionInputShape.String, CoercionAction.Fail))
            .withCoercionConfig(LogicalType.Integer, b -> b.setCoercion(CoercionInputShape.String, CoercionAction.Fail))
            .withCoercionConfig(LogicalType.Float, b -> b.setCoercion(CoercionInputShape.String, CoercionAction.Fail))
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .addModule(new JavaTimeModule())
            .build();

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        System.out.println(jsonString);
    }

    @Test
    void testDeserialize() throws IOException {
        //MismatchedInputException
        String json = "{\"aaa\": {\"s_S\": \"123\", \"i\": 1}, \"id\":\"923c0156-4b1a-45ef-a850-430fb23c131d\",\"beerName\":123,\"beerStyle\":\"Ale\",\"upc\":123123213,\"price\":12.91,\"createdDate\":\"2024-02-11T14:53:01.02388+08:00\",\"lastUpdatedDate\":\"2024-02-11T14:53:01.023908+08:00\"}\n";
        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);
        System.out.println(beerDto);
    }
}
