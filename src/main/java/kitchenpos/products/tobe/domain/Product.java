package kitchenpos.products.tobe.domain;

import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    @Column(name = "price")
    private ProductPrice productPrice;

    protected Product() {
    }

    public Product(String name, BigDecimal price) {
        validProductName(name);

        this.name = name;
        this.productPrice = new ProductPrice(price);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return productPrice.getPrice();
    }

    public void changeProductPrice(final BigDecimal price) {
        this.productPrice = new ProductPrice(price);
    }

    private void validProductName(String name) {
        if(StringUtils.isEmptyOrWhitespace(name)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productPrice);
    }
}
