package br.com.lessa.financialcontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private BigDecimal value;
    private LocalDate date;
    private Type type;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Category category;



    public enum Type{
        INCOME,
        EXPENSE
    }
}
