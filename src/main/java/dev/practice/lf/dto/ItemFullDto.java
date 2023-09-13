package dev.practice.lf.dto;

import dev.practice.lf.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemFullDto {
    private Long id;
    private String description;
    private String locationDescription;
    private LocalDateTime postDateTime;
    private LocalDate foundDate;
    private Set<CategoryDto> categories;
}
