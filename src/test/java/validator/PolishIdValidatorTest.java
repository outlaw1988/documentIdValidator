package validator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class PolishIdValidatorTest {

    private Validator idValidator;

    @Before
    public void before() {
        idValidator = new PolishIdValidator();
    }

    @Test
    @Parameters({"ALI524338", "CFI729542", "AKW555665"})
    public void testPositiveIdNumber(String id) {
        assertThat(idValidator.validate(id)).isTrue();
    }

    @Test
    public void testNegativeCheckSumInvalid() {
        String id = "AAA111112";
        assertThat(idValidator.validate(id)).isFalse();
    }

    @Test
    public void testNegativeEmpty() {
        String id = "";
        assertThat(idValidator.validate(id)).isFalse();
    }

    @Test
    public void testNegativeTooManyDigits() {
        String id = "ABC1231231";
        assertThat(idValidator.validate(id)).isFalse();
    }

    @Test
    public void testNegativeTooManyLetters() {
        String id = "ABCD123123";
        assertThat(idValidator.validate(id)).isFalse();
    }

    @Test
    public void testNegativeDigitInLetters() {
        String id = "C5I729542";
        assertThat(idValidator.validate(id)).isFalse();
    }

    @Test
    public void testNegativeLetterInDigits() {
        String id = "CFI72E542";
        assertThat(idValidator.validate(id)).isFalse();
    }

}