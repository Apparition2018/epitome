package org.ljh.mybatis.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryLanguageKey implements Serializable {
    private String countryCode;

    private String language;

    private static final long serialVersionUID = 1L;
}