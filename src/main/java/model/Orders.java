package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderid")
    private String orderid;
    @Basic
    @Column(name = "userid")
    private String userid;
    @Basic
    @Column(name = "prodid")
    private String prodid;
    @Basic
    @Column(name = "amount")
    private Integer amount;
    @Basic
    @Column(name = "addr")
    private String addr;
    @Basic
    @Column(name = "orderdate")
    private String orderdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderid, orders.orderid) && Objects.equals(userid, orders.userid) && Objects.equals(prodid, orders.prodid) && Objects.equals(amount, orders.amount) && Objects.equals(addr, orders.addr) && Objects.equals(orderdate, orders.orderdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid, userid, prodid, amount, addr, orderdate);
    }
}
