package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.StatusCodeDto;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.util.constant.StatusCodeClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusCodeService {
    private final StatusCodeMapper statusCodeMapper;
    private final StatusCodeRepository statusCodeRepository;

    public List<StatusCodeDto> getStatusCodesByClass(StatusCodeClass codeClass) {
        return statusCodeMapper.toDtoList(
                statusCodeRepository.findAllByCodeClass(codeClass.name())
        );
    }
}
