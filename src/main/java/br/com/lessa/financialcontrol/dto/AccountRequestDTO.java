package br.com.lessa.financialcontrol.dto;

import java.math.BigDecimal;

public record AccountRequestDTO(String id, String name, BigDecimal balance, String userId) {
}
