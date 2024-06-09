package br.brn.x3Integrator.model.masterDataBase;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "x3_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_customer")
    private Long cdnCustomer;

    @Column (name = "x3_bpcnum")
    private String cdnX3Customer;

    @Column (name = "x3_bpcsta")
    private Boolean status;

    @Column (name = "x3_xqrazao")
    private String razaoSocial;

    @Column (name = "creation_date")
    private LocalDateTime creationDate;

    @Column (name = "creation_user")
    private String creationUser;

    @Column (name = "update_date")
    private LocalDateTime updateDate;

    @Column (name = "update_user")
    private String updateUser;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    public Customer(Long cdnCustomer) {
        this.cdnCustomer = cdnCustomer;
    }
}
