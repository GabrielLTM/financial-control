package br.com.lessa.financialcontrol.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDTO(Long id, String username, String fullname, String password, List<String> roles) {
}
