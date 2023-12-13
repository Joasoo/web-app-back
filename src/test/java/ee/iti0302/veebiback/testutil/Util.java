package ee.iti0302.veebiback.testutil;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.FullNameDto;
import ee.iti0302.veebiback.dto.StatusCodeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Util {

    public static String getRandomEmail() {
        return getRandomString(12) + "@gmail.com";
    }

    public static Person getRandomPerson(List<Long> forbiddenIds) {
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

    public static Person getRandomPerson(Long forbiddenId) {
        return getRandomPerson(List.of(forbiddenId));
    }

    public static Person getRandomPerson() {
        return getRandomPerson(List.of());
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
