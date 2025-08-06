package br.com.lessa.financialcontrol.dto;


import java.io.Serializable;

public record AccountCredentialDTO(String username, String password, String fullname) implements Serializable {
}
