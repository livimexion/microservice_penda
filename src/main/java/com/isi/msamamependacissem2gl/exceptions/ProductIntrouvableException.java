package com.isi.msamamependacissem2gl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * auteur:Mame Penda Cisse
 * classe:M2GL
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIntrouvableException extends RuntimeException {
    public ProductIntrouvableException(String s) {
        super(s);
    }
}
