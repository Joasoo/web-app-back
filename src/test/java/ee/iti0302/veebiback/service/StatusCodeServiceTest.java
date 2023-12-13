package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.StatusCodeDto;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapperImpl;
import ee.iti0302.veebiback.util.constant.FriendshipStatus;
import ee.iti0302.veebiback.util.constant.RelationshipStatus;
import ee.iti0302.veebiback.util.constant.StatusCodeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static ee.iti0302.veebiback.testutil.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class StatusCodeServiceTest {
    @Mock
    private StatusCodeRepository statusCodeRepository;
    @Spy
    private StatusCodeMapper statusCodeMapper = new StatusCodeMapperImpl();
    @InjectMocks
    private StatusCodeService statusCodeService;

    private static StatusCode inRelationship;
    private static StatusCode married;
    private static StatusCode single;
    private static StatusCode complicated;
    private static StatusCodeDto inRelationshipDto;
    private static StatusCodeDto marriedDto;
    private static StatusCodeDto singleDto;
    private static StatusCodeDto complicatedDto;

    private static final StatusCodeClass _class = StatusCodeClass.REL_STATUS;
    private static final String inRelationshipText = "In a relationship";
    private static final String marriedText = "Married";
    private static final String singleText = "Single";
    private static final String complicatedText = "Complicated";

    @BeforeAll
    static void setUp() {
        inRelationship = getMockStatusCode(RelationshipStatus.REL_STATUS_R).asEntity();
        married = getMockStatusCode(RelationshipStatus.REL_STATUS_M).asEntity();
        single = getMockStatusCode(RelationshipStatus.REL_STATUS_S).asEntity();
        complicated = getMockStatusCode(RelationshipStatus.REL_STATUS_C).asEntity();

        inRelationshipDto = getMockStatusCode(RelationshipStatus.REL_STATUS_R).asDto();
        marriedDto = getMockStatusCode(RelationshipStatus.REL_STATUS_M).asDto();
        singleDto = getMockStatusCode(RelationshipStatus.REL_STATUS_S).asDto();
        complicatedDto = getMockStatusCode(RelationshipStatus.REL_STATUS_C).asDto();
    }

    @Test
    void getStatusCodesByClass_ExistingStatusCodeClass_StatusCodeDtosWithCorrectCodes() {
        var listEntities = List.of(inRelationship, married, single, complicated);
        var listDtos = List.of(inRelationshipDto, marriedDto, singleDto, complicatedDto);
        given(statusCodeRepository.findAllByCodeClass(_class.name()))
                .willReturn(listEntities);

        List<StatusCodeDto> result = statusCodeService.getStatusCodesByClass(_class);
        then(statusCodeMapper).should().toDtoList(listEntities);

        assertEquals(listDtos, result);
    }

}