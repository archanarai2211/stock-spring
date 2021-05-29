package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StockTradeRestController {

    private final String json = "application/json";

    @Autowired
    private StockTradeRepository repo;

    @PostMapping(path="/trades", consumes=json, produces = json)
    public ResponseEntity<StockTrade> createTrade(@RequestBody StockTrade data){
        data = repo.save(data);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }


    @GetMapping(value = "/trades/{id}",  produces = json)
    public ResponseEntity<StockTrade> getTrade(@PathVariable(value = "id") Integer id){
        Optional<StockTrade> result = repo.findById(id);
        return result.map(stockTrade -> new ResponseEntity<>(stockTrade, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/trades",  produces = json)
    public ResponseEntity<List<StockTrade>> getAllTrades(@RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "user_id", required = false)  Integer user_id,
                                                         @RequestParam(value = "userId", required = false)  Integer userId){
        Integer user = userId==null ? user_id : userId;
        List<StockTrade> data = getAllStockTrades(type, user);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    private List<StockTrade> getAllStockTrades(String type, Integer user_id) {
        if(type!=null && user_id!=null)
            return repo.findAllByTypeAndUser(type, user_id);
        if(type!=null)
            return repo.getAllByType(type);
        if(user_id!=null)
            return repo.getAllByUser(user_id);
        return repo.findAll();
    }

    @RequestMapping(value = "/trades/{id}", method = {RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<StockTrade> deleteTrade( @PathVariable(value = "id") String id){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}