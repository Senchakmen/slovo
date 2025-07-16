package com.asench.slovo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "user_language_pair")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class UserLanguagePair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_id", nullable = false)
    private Language sourceLanguage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_id", nullable = false)
    private Language targetLanguage;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    public UserLanguagePair(AppUser appUser, Language sourceLanguage, Language targetLanguage, boolean isDefault) {
        Objects.requireNonNull(appUser);
        Objects.requireNonNull(sourceLanguage);
        Objects.requireNonNull(targetLanguage);
        this.appUser = appUser;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.isDefault = isDefault;
    }
}
