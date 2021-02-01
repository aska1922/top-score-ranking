package api.topscoreranking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Getter
@Setter
public class Score extends AbstractEntity {

	@Column(nullable = false)
	private String player;
	@Column(nullable = false)
	@PositiveOrZero
	private Integer score;
	@Temporal(TemporalType.DATE)
	private Date time;

}
