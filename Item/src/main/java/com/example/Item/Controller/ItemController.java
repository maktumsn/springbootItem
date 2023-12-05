package com.example.Item.Controller;

import com.example.Item.Models.Items;
import com.example.Item.Models.ItemsResponse;
import com.example.Item.Services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<?> GetAllItem(@RequestParam(defaultValue ="0") String itemno,
                                              @RequestParam(defaultValue ="0") String ordernumber) {
        List<Items> items = new ArrayList<>();
        if (itemno.equals("0")) {
            if (ordernumber.equals("0")) {
                items = itemService.allItem();
            } else {
                items = itemService.GetByOrderId(Integer.parseInt(ordernumber));
            }


        } else {

            items = itemService.ItemById(Long.parseLong(itemno));
        }

        return ResponseEntity.ok().body(new ItemsResponse(items));
    }



    @PostMapping("/items/ordernumber")
    public ResponseEntity<Items> OrderItem(@RequestBody Items item, @RequestParam int ordernumber)
    {
        Items items= itemService.InsertItemofOrder(item);
        return ResponseEntity.accepted().body(items);
    }

    @DeleteMapping("/items/itemno")
    public void DeleteItem(@RequestParam long itemno)
    {
        List<?> items = itemService.ItemById(itemno);
        itemService.deleteOrderItem(itemno);

    }

    @PutMapping("/items/ordernumber")
    public ResponseEntity<Items> UpdateItem(@RequestBody Items item, @RequestParam int ordernumber)
    {
        Items items= itemService.updateItem(item);
        return ResponseEntity.accepted().body(items);
    }
}
