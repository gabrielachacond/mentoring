package com.ibm.geno.api.mentoring.component;

import com.ibm.geno.api.mentoring.exception.ApiEntityError;
import com.ibm.geno.api.mentoring.exception.BusinessLogicException;
import com.ibm.geno.api.mentoring.model.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public RuntimeException getExceptionEntityNotFound(String entityName, Long id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The " + entityName + " with id '" + id + "' does not exist"
        );

        return new BusinessLogicException(
                entityName + " does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException getExceptionPersonAlreadyExists(Person person) {
        ApiEntityError apiEntityError = new ApiEntityError(
                "Person",
                "AlreadyExists",
                "Person already exists with dni number " + person.getDni()
        );
        return new BusinessLogicException(
                person.getDni() + " already exists",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }
}
