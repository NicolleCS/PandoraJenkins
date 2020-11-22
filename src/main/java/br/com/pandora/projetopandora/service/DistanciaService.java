package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.vo.*;
import br.com.pandora.projetopandora.data.vo.filter.CepFilterVO;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.util.SleepUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static br.com.pandora.projetopandora.util.Constants.TOKEN_DISTANCE_MATRIX;

@Service
@Slf4j
public class DistanciaService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private SleepUtil sleep;

    @Autowired
    private ObjectMapper mapper;

    private String geocodeUrl = "https://api.distancematrix.ai/maps/api/geocode/json";

    private String distanceMatrixUrl = "https://api.distancematrix.ai/maps/api/distancematrix/json";

    public ResponseDistanceVO getDistance(CepFilterVO filterVO) {
        final LocationVO origem = getGeocode(filterVO.getCepOrigem()).getResult().get(0).getGeometry().getLocation();
        final LocationVO destino = getGeocode(filterVO.getCepDestino()).getResult().get(0).getGeometry().getLocation();

        final String requestUrl = distanceMatrixUrl +
                "?origins=" + origem.getLat() + "," + origem.getLng() +
                "&destinations=" + destino.getLat() + "," + destino.getLng() +
                "&key=" + TOKEN_DISTANCE_MATRIX;

        DistanceVO response = restTemplate.getForEntity(requestUrl, DistanceVO.class).getBody();

        return convert(response);
    }

    private GeocodeApiVO getGeocode(String cep) {
        try {
            return restTemplate.getForEntity(geocodeUrl + "?key=" + TOKEN_DISTANCE_MATRIX + "&address=" + cep, GeocodeApiVO.class).getBody();
        } catch (Exception e) {
            log.error("Erro ao realizar parse json", e);
        }
        throw new RegraNegocioException("Não foi possível resgatar geolocalização do CEP " + cep);
    }

    private ResponseDistanceVO convert(DistanceVO distanceVO) {
        try {
            if (!distanceVO.getStatus().equals("OK")) {
                throw new RegraNegocioException("CEPs inválidos");
            }
            ElementVO elementVO = distanceVO.getRows().get(0).getElements().get(0);
            ResponseDistanceVO vo = ResponseDistanceVO.builder()
                    .destinationAddress(distanceVO.getDestinationAddresses().get(0))
                    .originAddress(distanceVO.getOriginAddresses().get(0))
                    .distanceText(elementVO.getDistance().getText())
                    .distanceValue(elementVO.getDistance().getValue())
                    .durationText(elementVO.getDuration().getText())
                    .durationValue(elementVO.getDuration().getValue())
                    .build();
            return vo;
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao fazer converter response");
        }
    }


}
