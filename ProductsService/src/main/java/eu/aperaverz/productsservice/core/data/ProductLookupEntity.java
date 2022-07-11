package eu.aperaverz.productsservice.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "productLookup")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductLookupEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2387421L;

    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
