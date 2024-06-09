package br.brn.x3Integrator.model;

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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "x3_sales_quote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesQuote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_sales_quote")
    private Long cdnSalesQuote;

    @ManyToOne
    @JoinColumn(name = "cdn_site", referencedColumnName = "cdn_site")
    @Fetch(FetchMode.SELECT)
    private Site site;

    @Column (name = "x3_sohnum")
    private String cdnX3SalesQuote;

    @Column (name = "x3_quote_date")
    private LocalDate quoteDate;

    @Column (name = "x3_integration_status")
    private String x3IntegrationStatus;

    @Column (name = "x3_integration_message", columnDefinition = "TEXT")
    private String c3IntegrationMessage;

    @ManyToOne
    @JoinColumn(name = "cdn_customer", referencedColumnName = "cdn_customer")
    @Fetch(FetchMode.SELECT)
    private Customer customer;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "cdn_sales_quote", referencedColumnName = "cdn_sales_quote")
    @OrderBy("num_lin")
    private List<SalesQuoteProduct> salesQuoteProducts = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "cdn_sales_quote", referencedColumnName = "cdn_sales_quote")
    @OrderBy("cdn_sales_quote_x3_log")
    private List<SalesQuoteX3Log> logIntegration = new ArrayList<>();

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

    public SalesQuote(Long cdnCustomer) {
        this.cdnSalesQuote = cdnSalesQuote;
    }
}
