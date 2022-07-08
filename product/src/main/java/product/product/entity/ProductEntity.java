package product.product.entity;

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
@Table(name="warehouse")
public class ProductEntity {
    
    @Id
	@Column(name = "product_id",columnDefinition = "VARCHAR(36)")
	private String  productId; 

    @Column(columnDefinition = "VARCHAR(255)")
	private String name;

    @Column(columnDefinition = "INT(11)")
	private Integer price;

    @Column(columnDefinition = "INT(11)")
	private Integer balance;

	public ProductEntity(){}
}
