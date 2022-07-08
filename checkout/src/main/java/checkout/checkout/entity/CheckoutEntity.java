package checkout.checkout.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "check_out")
public class CheckoutEntity {
    
    @Id
    @Column(name = "check_out_id",columnDefinition = "VARCHAR(36)")
    private String checkOutId;

    @Column(columnDefinition = "VARCHAR(36)")
    private String orderId;

    @Column()
    private Date checkOutDate = new Date();

    public CheckoutEntity(){}
}
