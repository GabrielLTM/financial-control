package br.com.lessa.financialcontrol.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
