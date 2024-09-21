package team.hackerping.nanuri.global.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.StringUtils;

@Converter
public class JsonConverter<T> implements AttributeConverter<T, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (!StringUtils.hasText(dbData))
            return null;

        Class<?> entityClass = GenericTypeResolver.resolveTypeArgument(getClass(), JsonConverter.class);
        try {
            return (T) objectMapper.readValue(dbData, entityClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
