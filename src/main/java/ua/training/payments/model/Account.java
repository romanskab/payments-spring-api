package ua.training.payments.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import ua.training.payments.model.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(
            name = "id-generator",
            parameters = @Parameter(name = "prefix", value = "UA033052990000026"),
            strategy = "ua.training.payments.model.generator.IdGenerator"
    )
    private String id;

    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;

}
