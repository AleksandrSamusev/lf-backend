package dev.practice.lf.dto;

import dev.practice.lf.entity.Category;
import dev.practice.lf.entity.ContactMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String description;
    private String locationDescription;
    private LocalDateTime postDateTime = LocalDateTime.now();
    private LocalDate foundDate;
    private Set<Long> categoriesIds;
}
