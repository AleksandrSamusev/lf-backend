package dev.practice.lf.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location_description")
    private String locationDescription;

    @Column(name = "posted_on")
    private LocalDateTime postDateTime;

    @Column(name = "founded_on", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate foundDate;

    @ManyToMany /*(fetch = FetchType.EAGER, cascade = CascadeType.ALL)*/
    @JoinTable(name = "items_categories",
    joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;
}



