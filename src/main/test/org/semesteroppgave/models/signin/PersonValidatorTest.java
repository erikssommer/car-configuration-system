package org.semesteroppgave.models.signin;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {

    @Test
    void testValidName() {
        PersonValidator.testValidName("Ola");
        PersonValidator.testValidName("Ola Nordmann");
        PersonValidator.testValidName("Ola Nordmann Fjell");
        PersonValidator.testValidName("Ola Normann Fjell Daler");
        PersonValidator.testValidName("Ola-Normann Fjell");
        PersonValidator.testValidName("Ola-Normann Fjell Daler");
    }

    @Test
    void testInvalidNavn() {
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("ola"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola-Normann"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("ola17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola normann"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola normann Fjell"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola NoRmann fjell"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Ola NorMann Sommer"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidName("Dette er viktig!"));
    }

    @Test
    void testValidPhone() {
        PersonValidator.testValidPhone("12233212");
        PersonValidator.testValidPhone("00 47 12121212");
        PersonValidator.testValidPhone("00 47 121 21 212");
        PersonValidator.testValidPhone("00 47 121 21 212");
        PersonValidator.testValidPhone("+4712233212");
        PersonValidator.testValidPhone("+47 12233212");
        PersonValidator.testValidPhone("(+47)12233212");
        PersonValidator.testValidPhone("(+47) 12233212");
        PersonValidator.testValidPhone("(+47) 12 23 32 12");
        PersonValidator.testValidPhone("07911 123456");
        PersonValidator.testValidPhone("+44 7911 123456");
        PersonValidator.testValidPhone("754-3010");
        PersonValidator.testValidPhone("(541) 754-3010");
        PersonValidator.testValidPhone("+1-541-754-3010");
        PersonValidator.testValidPhone("1-541-754-3010");
        PersonValidator.testValidPhone("001-541-754-3010");
    }

    @Test
    void testInvalidTelefonnr() {
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone(""));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("7654321"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("NaN"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("-231"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("123-norway"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("1-541-æøå-3010"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("1-541-abc-3010"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("!%&/"));
        assertThrows(InvalidPhonenumberException.class, () -> PersonValidator.testValidPhone("123 123     123 12"));
    }

    @Test
    void testValidEmail() {
        PersonValidator.testValidEmail("henrik.lieng@oslomet.no");
        PersonValidator.testValidEmail("example@example.com");
        PersonValidator.testValidEmail("uk@domain.co.uk");
    }

    @Test
    void testInvalidEpost() {
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail(""));
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail("ola.normann"));
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail("@oslomet.no"));
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail("ola.normann@invalidDomain"));
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail("test@"));
        assertThrows(InvalidEmailException.class, () -> PersonValidator.testValidEmail(";bot@evil.com"));

    }

    @Test
    void testValidUsername() {
        PersonValidator.testValidUsername("Admin123");
        PersonValidator.testValidUsername("OlaNoramnn123");
    }

    @Test
    void testInvalidUsername() {
        assertThrows(InvalidUsernameException.class, () -> PersonValidator.testValidUsername("admin 123"));
        assertThrows(InvalidUsernameException.class, () -> PersonValidator.testValidUsername("Ola 123"));
    }

    @Test
    void testValidPassword() {
        PersonValidator.testValidPassword("Java13");
        PersonValidator.testValidPassword("OlaNormann123");
    }

    @Test
    void testInvalidPassword() {
        assertThrows(InvalidPasswordException.class, () -> PersonValidator.testValidPassword("Java 13"));
        assertThrows(InvalidPasswordException.class, () -> PersonValidator.testValidPassword("Ola Normann123"));
    }
}