package ru.vyatsu.fileconverter.bean;

import lombok.*;

/**
 * Класс для хранения информации о процессоре ноутбука
 */
@Data
@NoArgsConstructor
public class Cpu {
    private String model;
    private int numCores;
    private double frequency;
}
