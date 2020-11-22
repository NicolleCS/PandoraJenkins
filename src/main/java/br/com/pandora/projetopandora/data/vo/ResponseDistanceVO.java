package br.com.pandora.projetopandora.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDistanceVO {

    private String destinationAddress;

    private String originAddress;

    private String distanceText;

    private Long distanceValue;

    private String durationText;

    private Long durationValue;
}
