package com.spring.backend.easyvet.util;

import java.util.Optional;

public enum EAppoinmentStatus {
	CONFIRMED, NOT_CONFIRMED;
	
	public static Optional<EAppoinmentStatus> check(String val) {
        try { return Optional.of(EAppoinmentStatus.valueOf(val)); }
        catch (Exception e) {/* do nothing */}
        return Optional.empty();
    }
}
