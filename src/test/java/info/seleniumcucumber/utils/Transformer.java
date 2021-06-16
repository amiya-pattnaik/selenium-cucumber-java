package info.seleniumcucumber.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;

import java.lang.reflect.Type;
import java.util.Map;

class Transformer implements ParameterByTypeTransformer, TableEntryByTypeTransformer,
        TableCellByTypeTransformer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Object transform(String s, Type type) {
        return objectMapper.convertValue(s, objectMapper.constructType(type));
    }

    public <T> T transform(Map<String, String> map, Class<T> aClass,
                           TableCellByTypeTransformer tableCellByTypeTransformer) {
        return objectMapper.convertValue(map, aClass);
    }

    public <T> T transform(String s, Class<T> aClass) {
        return objectMapper.convertValue(s, aClass);
    }
}
