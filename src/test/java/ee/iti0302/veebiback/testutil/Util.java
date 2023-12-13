package ee.iti0302.veebiback.testutil;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.*;
import ee.iti0302.veebiback.util.constant.FriendshipStatus;
import ee.iti0302.veebiback.util.constant.RelationshipStatus;
import ee.iti0302.veebiback.util.constant.StatusCodeClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Util {

    public static Person getMockPerson(List<Long> forbiddenIds) {
        Long randomId = new Random().nextLong(100);
        while (forbiddenIds.contains(randomId)) {
            randomId = new Random().nextLong(100);
        }
        return Person.builder()
                .id(randomId)
                .firstName(getRandomString(6))
                .lastName(getRandomString(8))
                .email(getRandomEmail())
                .password(getRandomString(15))
                .dateOfBirth(LocalDate.parse("2000-10-20"))
                .build();
    }

    public static Person getMockPerson(Long forbiddenId) {
        return getMockPerson(List.of(forbiddenId));
    }

    public static Person getMockPerson() {
        return getMockPerson(List.of());
    }

    public static FullNameDto getFullNameDto(Person person) {
        return new FullNameDto(person.getFirstName(), person.getLastName());
    }

    public static StatusCodeDto getStatusCodeDto(StatusCode code) {
        return StatusCodeDto.builder()
                .code(code.getCode())
                .codeClass(code.getCodeClass())
                .value(code.getValue())
                .build();
    }

    public static List<String> asStringList(Enum<?>... enums) {
        return Arrays.stream(enums).map(Enum::name).toList();
    }

    public static FriendListDto getFriendListDto(Person person, StatusCode code) {
        FullNameDto nameDto = getFullNameDto(person);
        StatusCodeDto statusDto = getStatusCodeDto(code);
        return FriendListDto.builder()
                .name(nameDto)
                .status(statusDto)
                .id(person.getId())
                .build();
    }

    public static StatusCodeMock getMockStatusCode(FriendshipStatus status) {
        final StatusCodeClass _class = StatusCodeClass.FR_STATUS;
        switch (status) {
            case FR_STATUS_A -> {
                return StatusCodeMock.builder()
                        .value("Accepted")
                        .code(FriendshipStatus.FR_STATUS_A.name())
                        .codeClass(_class.name())
                        .build();
            }
            case FR_STATUS_S -> {
                return StatusCodeMock.builder()
                        .value("Sent")
                        .code(FriendshipStatus.FR_STATUS_S.name())
                        .codeClass(_class.name())
                        .build();
            }
            case FR_STATUS_R -> {
                return StatusCodeMock.builder()
                        .value("Received")
                        .code(FriendshipStatus.FR_STATUS_R.name())
                        .codeClass(_class.name())
                        .build();
            }
        }
        throw new IllegalArgumentException();
    }

    public static StatusCodeMock getMockStatusCode(RelationshipStatus status) {
        final StatusCodeClass _class = StatusCodeClass.REL_STATUS;

        switch (status) {
            case REL_STATUS_C -> {
                return StatusCodeMock.builder()
                        .code(RelationshipStatus.REL_STATUS_C.name())
                        .codeClass(_class.name())
                        .value("Complicated")
                        .build();
            }
            case REL_STATUS_M -> {
                return StatusCodeMock.builder()
                        .code(RelationshipStatus.REL_STATUS_M.name())
                        .codeClass(_class.name())
                        .value("Married")
                        .build();
            }
            case REL_STATUS_R -> {
                return StatusCodeMock.builder()
                        .code(RelationshipStatus.REL_STATUS_R.name())
                        .codeClass(_class.name())
                        .value("In a relationship")
                        .build();
            }
            case REL_STATUS_S -> {
                return StatusCodeMock.builder()
                        .code(RelationshipStatus.REL_STATUS_S.name())
                        .codeClass(_class.name())
                        .value("Single")
                        .build();
            }
        }
        throw new IllegalArgumentException();
    }

    public static Post getMockPost(Person person, int day, int month, int year, List<Long> forbiddenIds) {
        LocalDateTime localDate = LocalDateTime.of(year, month, day, 0, 0);
        Long randomId = new Random().nextLong(100);
        while (forbiddenIds.contains(randomId)) {
            randomId = new Random().nextLong(100);
        }
        return Post.builder()
                .id(randomId)
                .content(getRandomString(100))
                .person(person)
                .createdAt(localDate)
                .build();
    }

    public static Post getMockPost(Person person, int day, int month, int year, Long forbiddenId) {
        return getMockPost(person, day, month, year, List.of(forbiddenId));
    }

    public static Post getMockPost(Person person, int day, int month, int year) {
        return getMockPost(person, day, month, year, List.of());
    }

    public static PostDto getPostDto(Post post) {
        return PostDto.builder()
                .author(getPersonFullNameDto(post.getPerson()))
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .id(post.getId())
                .build();
    }

    public static PersonFullNameDto getPersonFullNameDto(Person person) {
        var dto = new PersonFullNameDto(person.getFirstName(), person.getLastName(), person.getId());
        return dto;
    }

    // ---------------------------- Private ----------------------------

    private static String getRandomEmail() {
        return getRandomString(12) + "@gmail.com";
    }

    private static String getRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
