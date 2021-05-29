package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

    @Query(value = "SELECT * FROM stocktrade s WHERE s.type = :type and s.userid= :userId", nativeQuery = true)
    List<StockTrade> findAllByTypeAndUser(@Param("type")  String type,@Param("userId")   Integer userId);

    @Query(value = "SELECT * FROM stockTrade s WHERE s.type = ?1", nativeQuery = true)
    List<StockTrade> getAllByType(String type);

    @Query(value = "SELECT * FROM stocktrade s WHERE s.userid= :userId", nativeQuery = true)
    List<StockTrade> getAllByUser(@Param("userId")  Integer user_id);
}
