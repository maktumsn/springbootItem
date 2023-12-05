package com.example.Item.Repository;
import com.example.Item.Models.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ItemRepository extends CrudRepository<Items,Long> {


    List<Items> findByordernumber(int ordernumber);
}
