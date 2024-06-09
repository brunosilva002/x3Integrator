package br.brn.x3Integrator.model.masterDataBase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "x3_product_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_product_customer")
    private Long cdnProduct;

    @ManyToOne
    @JoinColumn(name = "cdn_product", referencedColumnName = "cdn_product")
    @Fetch(FetchMode.SELECT)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cdn_customer", referencedColumnName = "cdn_customer")
    @Fetch(FetchMode.SELECT)
    private Customer customer;

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

    public ProductCustomer(Long cdnProduct) {
        this.cdnProduct = cdnProduct;
    }
}
