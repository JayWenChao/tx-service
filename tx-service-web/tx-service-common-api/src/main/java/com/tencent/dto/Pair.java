package com.tencent.dto;

import com.google.gson.Gson;
import lombok.*;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Pair {

    private int numPage;
    private int seedId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
