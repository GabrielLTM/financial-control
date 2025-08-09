package br.com.lessa.financialcontrol.dto;

import br.com.lessa.financialcontrol.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
