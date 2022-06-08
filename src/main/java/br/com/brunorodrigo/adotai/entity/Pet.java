package br.com.brunorodrigo.adotai.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "pets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pet {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String idade;
    @Column(nullable = false)
    private String raca;

    @Column(columnDefinition="TEXT")
    private String profilePicture;

    @ManyToOne
    private User user;
}
