package br.brn.x3Integrator.model.masterDataBase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "x3_sales_quote_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesQuoteProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_sales_quote_product")
    private Long cdnSalesQuoteProduct;

    @Column (name = "num_lin")
    private Long lineNumber;

    @ManyToOne
    @JoinColumn(name = "cdn_sales_quote", referencedColumnName = "cdn_sales_quote")
    @Fetch(FetchMode.SELECT)
    private SalesQuote salesQuote;

    @Column (name = "x3_soplin")
    private Long x3LineNumber;

    @ManyToOne
    @JoinColumn(name = "cdn_product", referencedColumnName = "cdn_product")
    @Fetch(FetchMode.SELECT)
    private Product product;

    @Column (name = "x3_qtystu")
    private Double qty;

    @Column (name = "x3_gropri")
    private Double netPrice;

    @Column (name = "net_price_total")
    private Double netPriceToltal;

    @Column (name = "x3_shidat")
    private LocalDate deliveryDate;


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
}
