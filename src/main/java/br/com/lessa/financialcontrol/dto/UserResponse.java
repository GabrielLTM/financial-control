package br.com.lessa.financialcontrol.dto;

import br.com.lessa.financialcontrol.entity.UserRole;
import lombok.Builder;

@Builder
public record UserResponse(String id, String login, UserRole role) {
}
