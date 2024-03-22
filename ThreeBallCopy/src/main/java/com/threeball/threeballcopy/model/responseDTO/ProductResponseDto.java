package com.threeball.threeballcopy.model.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude
@JsonPropertyOrder({
        "name",
        "description",
        "price"
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    @JsonIgnore
    private int idproducts;
    @JsonProperty("name")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private int price;
}
