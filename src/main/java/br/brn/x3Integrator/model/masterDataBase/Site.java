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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "x3_site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cdn_site")
    private Long cdnSite;

    @Column(name ="x3_fcy")
    private String cdnX3Site;


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

    public Site(Long cdnCustomer) {
        this.cdnSite = cdnSite;
    }
}
