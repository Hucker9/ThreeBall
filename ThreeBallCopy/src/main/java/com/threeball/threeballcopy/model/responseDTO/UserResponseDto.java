package com.threeball.threeballcopy.model.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.threeball.threeballcopy.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude
@JsonPropertyOrder({
        "name",
        "surname",
        "year"
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @JsonIgnore
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("year")
    private int year;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String verify;
    @JsonIgnore
    private Status status;
    @JsonIgnore
    private String resetToken;
}