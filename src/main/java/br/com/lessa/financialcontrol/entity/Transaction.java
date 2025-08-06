package br.com.lessa.financialcontrol.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private BigDecimal value;
    private LocalDate date;
    private Type type;
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


    public enum Type{
        INCOME,
        EXPENSE
    }
}
