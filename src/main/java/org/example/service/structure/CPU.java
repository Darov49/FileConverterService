package org.example.service.structure;

import lombok.*;

/**
 * Класс для хранения информации о процессоре ноутбука
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CPU {
    private String model;
    private int numCores;
    private double frequency;
}
