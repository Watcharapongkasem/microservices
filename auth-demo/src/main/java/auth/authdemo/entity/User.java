package auth.authdemo.entity;

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
@Table(name="user")
public class User {
    
    @Id
	@Column(name = "user_id",columnDefinition = "VARCHAR(36)")
	private String userId; 

    @Column(columnDefinition = "VARCHAR(255)")
	private String userName;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String passwordHash;

    @Column(columnDefinition = "VARCHAR(255)")
	private String passwordSalt;

    public User(){}
}
