package me.tdcarefor.tdcloud.user.query.repository;

import me.tdcarefor.tdcloud.user.query.entity.UserEntity;
import me.tdcarefor.tdcloud.repository.FindByIdAndPagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by xsx on 16/8/23.
 */
public interface UserQueryRepository extends FindByIdAndPagingAndSortingRepository<UserEntity,String> {

    List<UserEntity> findByOpenId(String openId);

    UserEntity findByUserName(String userName);

    List<UserEntity> findByUnionIdAndUserType(String unionId, Integer userType);
}
