package org.example.exojee.DTO;

import java.util.Map;

public record Result (int overallResult, Map<Integer, Boolean> responsesByIndex) {
}
