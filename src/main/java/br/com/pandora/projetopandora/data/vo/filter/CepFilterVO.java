package br.com.pandora.projetopandora.data.vo.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepFilterVO {

    private String cepOrigem;

    private String cepDestino;

}
