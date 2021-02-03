package ua.training.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.training.payments.model.enums.State;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class AccountDto {

    @JsonProperty(access = READ_ONLY)
    private String id;

    private BigDecimal balance;

    @JsonProperty(access = READ_ONLY)
    private State state;
}
