package com.evan.homemaking.common.model.dto.base;

import org.springframework.lang.NonNull;

import static com.evan.homemaking.common.utils.BeanUtil.updateProperties;

/**
 * @ClassName OutputConverter
 * @Description Converter interface for output DTO.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 2:45
 */
public interface OutputConverter<DTO extends OutputConverter<DTO, DOMAIN>, DOMAIN> {
    /**
     * Convert from domain.(shallow)
     *
     * @param domain domain data
     * @return converted dto data
     */
    @NonNull
    default <T extends DTO> T convertFrom(@NonNull DOMAIN domain) {

        updateProperties(domain, this);

        return (T) this;
    }
}
