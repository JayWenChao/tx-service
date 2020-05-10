package com.tencent.dto;

import com.google.gson.Gson;
import lombok.*;

import java.util.List;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class WrapperDTO {

    private String name;
    private Integer id;
    private List<WrapperDTO> children;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
