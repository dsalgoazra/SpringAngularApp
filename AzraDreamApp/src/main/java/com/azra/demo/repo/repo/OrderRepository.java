package com.azra.demo.repo.repo;

import com.azra.demo.domain.OrderDTO;
import com.azra.demo.repo.entity.Order;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface OrderRepository extends CrudRepository<Order, Long> {

    String ORDER_CACHE_NAME = "orderCacheName";

//    @Query(value = "FROM order o WHERE  " +
//            " o.orderStatus = :orderStatus order by o.modifiedTime desc")//
//    @Cacheable(value = ORDER_CACHE_NAME)
//    Iterable<Order> fetchByOrderStatus(@Param("orderStatus") String status);
//
//    @Cacheable(value = ORDER_CACHE_NAME)
//    Iterable<Order> findTopByModifiedDtmDesc( );

    @CacheEvict(value = ORDER_CACHE_NAME, allEntries = true)
    Order save(Order order);
}
