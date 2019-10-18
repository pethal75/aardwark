package com.aardwark.players.model.player;

import java.util.List;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;

public interface PlayerEntityRepository extends CrudRepository<PlayerEntity, Integer> {

    List<PlayerEntity> findByIdPlayer(int idPlayer);
    List<PlayerEntity> findByModifiedAt(Date modifiedAt);
    List<PlayerEntity> findByModifiedAtAfter(Date modifiedAt);
    List<PlayerEntity> findByModifiedAtBefore(Date modifiedAt);
    List<PlayerEntity> findByCreatedAt(Date createdAt);
    List<PlayerEntity> findByCreatedAtAfter(Date createdAt);
    List<PlayerEntity> findByCreatedAtBefore(Date createdAt);
    List<PlayerEntity> findByCode(String code);
    List<PlayerEntity> findByMemberId(String memberId);
    List<PlayerEntity> findByNameTypeId(int nameTypeId);
    List<PlayerEntity> findByFirstname(String firstname);
    List<PlayerEntity> findByMiddlename(String middlename);
    List<PlayerEntity> findByLastname(String lastname);
    List<PlayerEntity> findByLastname2(String lastname2);
    List<PlayerEntity> findByGenderId(int genderId);
    List<PlayerEntity> findByDateOfBirth(Date dateOfBirth);
    List<PlayerEntity> findByDateOfBirthAfter(Date dateOfBirth);
    List<PlayerEntity> findByDateOfBirthBefore(Date dateOfBirth);
    List<PlayerEntity> findByNationality(String nationality);
    List<PlayerEntity> findByActive(boolean active);

}
