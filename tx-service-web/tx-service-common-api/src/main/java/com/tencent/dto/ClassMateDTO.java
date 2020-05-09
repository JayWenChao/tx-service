package com.tencent.dto;

import com.google.gson.Gson;
import lombok.*;
import sun.dc.pr.PRError;

import java.math.BigDecimal;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class ClassMateDTO {



    private BigDecimal price;
    private String users;
    private String agency;
    private String link;
    private String title;
    private int seedId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
