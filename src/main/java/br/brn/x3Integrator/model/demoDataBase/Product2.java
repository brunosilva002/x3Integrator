package br.brn.x3Integrator.model.demoDataBase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "x3_product1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_product")
    private Long cdnProduct;

    @Column (name = "x3_itmref")
    private String cdnX3Product;

    @Column (name = "x3_ydescsefaz")
    private String description;

    @Column (name = "x3_itmsta")
    private String productStatus;

    @Column (name = "creation_date")
    private LocalDateTime creationDate;

    @Column (name = "creation_user")
    private String creationUser;

    @Column (name = "update_date")
    private LocalDateTime updateDate;

    @Column (name = "update_user")
    private String updateUser;

    @Column (name = "update_user3")
    private String updateUser4;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    public Product2(Long cdnProduct) {
        this.cdnProduct = cdnProduct;
    }
}
