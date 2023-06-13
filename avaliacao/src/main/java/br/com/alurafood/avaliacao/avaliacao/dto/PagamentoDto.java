package br.com.alurafood.avaliacao.avaliacao.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expirationAt;
    private String securityCode;
    private StatusPagamento status;
    private Long idOrder;
    private Long paymentType;
}
