package me.zhengjie.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
public class ModelMapperUtils {
    public static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.addConverter(new Converter<Date, String>() {
            @Override
            public String convert(MappingContext<Date, String> context) {
                return DateUtil.formatSecond(context.getSource());
            }
        });
        mapper.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(MappingContext<String, Date> context) {
                return DateUtil.formatSecond(context.getSource());
            }
        });

        Configuration configuration = mapper.getConfiguration();
        configuration.setAmbiguityIgnored(true);
        // 匹配模式设置为严格模式，避免属性名称相似也能被覆盖
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T> List<T> mapList(Collection<?> collection, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(obj -> mapper.map(obj, targetClass)).collect(Collectors.toList());
    }

    public static <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationType);
    }

    public static void map(Object source, Object destination) {
        if (source != null) {
            mapper.map(source, destination);
        }
    }
}
