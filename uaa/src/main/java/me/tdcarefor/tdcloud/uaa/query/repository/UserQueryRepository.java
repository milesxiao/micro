package me.tdcarefor.tdcloud.uaa.query.repository;

import java.util.List;
import me.tdcarefor.tdcloud.repository.FindByIdAndPagingAndSortingRepository;
import me.tdcarefor.tdcloud.uaa.query.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by xsx on 16/8/23.
 */
public interface UserQueryRepository extends FindByIdAndPagingAndSortingRepository<UserEntity,String> {

    UserEntity findByOpenId(String openId);

    UserEntity findByUserName(String userName);

    List<UserEntity> findByUnionIdAndUserType(String unionId, Integer userType);
}
