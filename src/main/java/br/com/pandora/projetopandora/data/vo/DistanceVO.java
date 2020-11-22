package br.com.pandora.projetopandora.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DistanceVO {

    @JsonProperty(value = "destination_addresses")
    private List<String> destinationAddresses;

    @JsonProperty(value = "origin_addresses")
    private List<String> originAddresses;

    private List<RowVO> rows;

    private String status;
}
