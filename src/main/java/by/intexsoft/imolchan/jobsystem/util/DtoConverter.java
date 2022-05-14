package by.intexsoft.imolchan.jobsystem.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoConverter {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static  <T> T convert(Object obj, Class<T> clazz) {
        return MODEL_MAPPER.map(obj, clazz);
    }
}
