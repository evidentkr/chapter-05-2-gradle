package com.rubypaper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString(exclude="member")
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false) //nullable=false > INNER JOIN
	private Member member;

	//setMember 를 재정의 해야만 양방향 테스트가 된다
	public void setMember(Member member) {
		this.member = member;
		//멤바가 보드 리스트를 포함하게 추가
		member.getBoardList().add(this);
	}
}