package br.com.lessa.financialcontrol.dto;

import br.com.lessa.financialcontrol.entity.UserRole;
import lombok.Builder;

@Builder
public record UserRequest(String id, String name, String login, String password, UserRole role) {
}
