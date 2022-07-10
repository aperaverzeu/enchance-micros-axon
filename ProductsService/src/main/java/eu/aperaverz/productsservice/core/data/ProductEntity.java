package eu.aperaverz.productsservice.core.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "products")
@Data
public class ProductEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -227264951080660124L;
    @Id
    @Column(unique = true)
    String productId;
    @Column(unique = true)
    String title;
    BigDecimal price;
    Integer amount;
}
