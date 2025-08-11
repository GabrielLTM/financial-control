package br.com.lessa.financialcontrol.dto;

import lombok.Builder;

@Builder
public record AccountResponseDTO(String id, String name, String userId) {
}
