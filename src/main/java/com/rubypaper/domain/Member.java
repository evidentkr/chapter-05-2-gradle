package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="boardList")
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;

	//mappedBy="member" > 주인이 아님 설정 (연관관계의 주인은 테이블에 외래키가 있는 테이블이어야 함)
	//cascade=CascadeType.ALL > 멤버를 삭제하면 멤버에 물려있는 보드도 삭제됨
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
 	
}
