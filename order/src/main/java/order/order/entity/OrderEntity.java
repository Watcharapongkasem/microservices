package order.order.entity;

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
@Table(name="product_order")
public class OrderEntity {    

    @Id
	@Column(name = "order_id",columnDefinition = "VARCHAR(36)")
	private String  orederId; 

    @Column(columnDefinition = "VARCHAR(36)")
	private String  productId; 

    @Column(columnDefinition = "VARCHAR(255)")
	private String name;

    @Column(columnDefinition = "INT(11)")
	private Integer value;

    @Column(columnDefinition = "VARCHAR(36)")
	private String  userId; 

    @Column(columnDefinition = "boolean default false")
	private Boolean status;

	public OrderEntity(){}
}
