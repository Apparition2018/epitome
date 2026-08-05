package org.ljh.mybatis.entity.normal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryLanguageKey implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String countryCode;

    private String language;
}
