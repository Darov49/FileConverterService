package org.example.bean;

import lombok.*;

/**
 * Класс для хранения информации о видеокарте ноутбука
 */
@Data
@NoArgsConstructor
public class Gpu {
    private String model;
    private int volume;
}
