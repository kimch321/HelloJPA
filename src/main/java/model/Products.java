package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "prodid")
    private String prodid;
    @Basic
    @Column(name = "prodname")
    private String prodname;
    @Basic
    @Column(name = "stock")
    private Integer stock;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "maker")
    private String maker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(prodid, products.prodid) && Objects.equals(prodname, products.prodname) && Objects.equals(stock, products.stock) && Objects.equals(price, products.price) && Objects.equals(maker, products.maker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodid, prodname, stock, price, maker);
    }
}
