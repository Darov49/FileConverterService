package org.example.bean.dto;

import lombok.*;

/**
 * Класс для хранения информации о видеокарте ноутбука
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GPU {
    private String model;
    private int volume;
}
