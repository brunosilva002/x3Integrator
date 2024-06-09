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
@Table(name = "x3_sales_quote_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesQuoteX3Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_sales_quote_x3_log")
    private Long cdnSalesQuotelogIntegration;

    @ManyToOne
    @JoinColumn(name = "cdn_sales_quote", referencedColumnName = "cdn_sales_quote")
    @Fetch(FetchMode.SELECT)
    private SalesQuote salesQuote;

    @Column (name = "x3_send_body", columnDefinition = "TEXT")
    private String x3SendBody;

    @Column (name = "x3_response_body", columnDefinition = "TEXT")
    private String x3ResponseBody;

    @Column (name = "x3_response_status_ws")
    private String x3ResposneStatusWs;

    @Column (name = "x3_response_headers", columnDefinition = "TEXT")
    private String x3ResponseHeaders;

    @Column (name = "x3_error_messages", columnDefinition = "TEXT")
    private String x3ErrorMessages;

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

    public SalesQuoteX3Log(Long cdnCustomer) {
        this.cdnSalesQuotelogIntegration = cdnSalesQuotelogIntegration;
    }
}
