package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
            .withCoercionConfig(LogicalType.Boolean, b -> {
                b.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail);
                b.setCoercion(CoercionInputShape.String, CoercionAction.Fail);
            })
            .withCoercionConfig(LogicalType.Integer, h -> h.setCoercion(CoercionInputShape.String, CoercionAction.AsEmpty))
            .withCoercionConfig(LogicalType.Float, b -> b.setCoercion(CoercionInputShape.String, CoercionAction.Fail))
            .withCoercionConfig(LogicalType.Textual, t -> t.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail))
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(MapperFeature.ALLOW_COERCION_OF_SCALARS)
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
        String json = """
                {
                  "id" : "923c0156-4b1a-45ef-a850-430fb23c131d",
                  "beerName" : "123",
                  "beerStyle" : 123,
                  "upc" : 123123213,
                  "b" : null,
                  "map" : null,
                  "aaa" : {
                    "b" : false,
                    "i" : 1,
                    "s_S" : "123"
                  },
                  "price" : "12.1",
                  "createdDate" : 1707634381.023880000,
                  "lastUpdatedDate" : 1707634381.023908000
                }
                """;
        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);
        System.out.println(beerDto);
    }
}
