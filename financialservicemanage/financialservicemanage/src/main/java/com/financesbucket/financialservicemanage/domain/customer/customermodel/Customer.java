package com.financesbucket.financialservicemanage.domain.customer.customermodel;

import com.financesbucket.financialservicemanage.domain.customer.customermodel.constants.CustomerConstants;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.exceptions.ExceptionUtil;
import com.financesbucket.financialservicemanage.domain.exceptions.constans.RestrictionsConstants;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder(toBuilder = true)
public class Customer {
    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private Date fechaNacimiento;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public void validCustomer() {
        this.isNotNullOrEmpty();
        this.isValidateLenght();
        this.isValidEmail();
        this.isUnderAge();

    }

    private void isNotNullOrEmpty() {
        if (this.tipoIdentificacion == null || this.numeroIdentificacion == null || this.fechaNacimiento == null
                || this.tipoIdentificacion.isBlank() || this.numeroIdentificacion.isBlank()) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }

    public boolean isValidateLenght() throws BussinessException {
        if (this.primerNombre.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.segundoNombre.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.primerApellido.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.segundoApellido.length() < RestrictionsConstants.LIMIT_MIN_NAME) {
            throw new BussinessException(ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_STATUS,
                    ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_DETAIL,
                    ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_CODE);
        }

        return true;
    }

    public boolean isValidEmail() throws BussinessException {
        if (!validateFormatEmail(this.correoElectronico)) {
            throw new BussinessException(ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_STATUS,
                    ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_DETAIL,
                    ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_CODE);
        }

        return true;
    }

    public boolean validateFormatEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(CustomerConstants.EMAIL_REGEX);

    public boolean isUnderAge() throws BussinessException {
        if (isUnderAgeValidation(this.fechaNacimiento)) {
            throw new BussinessException(ExceptionUtil.IS_A_MINOR_EXCEPTION_STATUS,
                    ExceptionUtil.IS_A_MINOR_EXCEPTION_DETAIL,
                    ExceptionUtil.IS_A_MINOR_EXCEPTION_CODE);
        }
        return true;
    }

    private boolean isUnderAgeValidation(Date fechaNacimiento) {
        return validateAgeLimit(fechaNacimiento);
    }

    public boolean validateAgeLimit(Date bithDate) {
        Date currentDate = new Date(System.currentTimeMillis());
        long edadMillis = currentDate.getTime() - bithDate.getTime();
        long age = edadMillis / (1000L * 60 * 60 * 24 * 365);
        return age < 18;
    }

}
