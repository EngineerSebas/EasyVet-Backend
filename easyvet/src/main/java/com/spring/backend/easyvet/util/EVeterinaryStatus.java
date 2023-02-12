package com.spring.backend.easyvet.util;

import java.util.Optional;

public enum EVeterinaryStatus {
	DISPONIBLE, EN_CONSULTA, NO_DISPONIBLE;
	
	public static Optional<EVeterinaryStatus> check(String val) {
        try { return Optional.of(EVeterinaryStatus.valueOf(val)); }
        catch (Exception e) {/* do nothing */}
        return Optional.empty();
    }
}
