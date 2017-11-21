package me.tdcarefor.tdcloud.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by xxg on 16/8/20.
 */
@NoRepositoryBean
public interface FindByIdAndPagingAndSortingRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    T findById(String id);
    T findById(Integer id);
    List<T> findByIdIn(Collection<String> id);
}
