package com.asench.slovo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false, unique = true)
    private String code;          // ISO 639-1, e.g. "en"

    @Column(nullable = false)
    private String name;          // English display name

    public Language(String code, String name) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(name);
        this.code  = code.toLowerCase();
        this.name  = name;
    }
}
