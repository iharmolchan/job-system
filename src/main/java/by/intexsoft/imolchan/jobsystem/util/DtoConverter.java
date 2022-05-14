package by.intexsoft.imolchan.jobsystem.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoConverter {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static <T> T convert(Object obj, Class<T> clazz) {
        return MODEL_MAPPER.map(obj, clazz);
    }
}
