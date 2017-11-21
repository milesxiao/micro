package me.tdcarefor.tdcloud.open.query;

import java.util.List;
import me.tdcarefor.tdcloud.repository.FindByIdAndPagingAndSortingRepository;

/**
 * Created by xsx on 16/8/23.
 */
public interface WxAppInfoQueryRepository extends FindByIdAndPagingAndSortingRepository<WxAppInfoEntity,String> {

  WxAppInfoEntity findByHospitalIdAndAppIdAndStatus(String hospitalId, String appId, Integer status);

  WxAppInfoEntity findByAppIdAndStatus(String appId, Integer status);

  List<WxAppInfoEntity> findByHospitalIdAndStatus(String hospitalId, Integer status);

  WxAppInfoEntity findByIdAndStatus(String id, Integer status);

}
