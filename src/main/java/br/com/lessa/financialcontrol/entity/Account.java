package br.com.lessa.financialcontrol.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
