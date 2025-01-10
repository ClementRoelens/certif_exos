package org.example.exojee.DTO;

import java.util.Map;

public record QuizzDTO (int id, Map<Integer, String> questions) {
}
