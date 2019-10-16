package com.aardwark.players.model.player;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="player", schema = "players") 
@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name="hibernate_sequence", sequenceName = "public.hibernate_sequence", schema = "public", allocationSize=1)
    //@SequenceGenerator(name="player_generator", sequenceName = "player_seq", allocationSize=1)
    @Column(name="id_player")
    private int idPlayer;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="code")
    private String code;

    @Column(name="member_id")
    private String memberId;

    @Column(name="name_type_id")
    private int nameTypeId;

    @Column(name="firstname")
    private String firstname;

    @Column(name="middlename")
    private String middlename;

    @Column(name="lastname")
    private String lastname;

    @Column(name="lastname2")
    private String lastname2;

    @Column(name="gender_id")
    private int genderId;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="nationality")
    private String nationality;

    @Column(name="active")
    private boolean active;


    public PlayerEntity() {}

    @Override
    public String toString() {
        return String.format(
                "PlayerEntity",
                idPlayer);
    }

	public int getId() {
		return idPlayer;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getNameTypeId() {
		return nameTypeId;
	}

	public void setNameTypeId(int nameTypeId) {
		this.nameTypeId = nameTypeId;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLastname2() {
		return lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}
	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

