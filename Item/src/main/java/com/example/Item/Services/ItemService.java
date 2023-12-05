package com.example.Item.Services;

import com.example.Item.Exceptions.InvalidItemFormat;
import com.example.Item.Exceptions.ItemNotFoundException;
import com.example.Item.Models.Data;
import com.example.Item.Models.Items;
import com.example.Item.Models.Orders;
import com.example.Item.Models.OrdersResponse;
import com.example.Item.Repository.ItemRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository repository;
    private Orders order;

    public ItemService(ItemRepository repository, Orders order) {
        this.repository = repository;
        this.order = order;
    }

    @Cacheable(cacheNames = "item")
    public List<Items> allItem()
    {
        return (List<Items>) repository.findAll();
    }
    @Cacheable(cacheNames = "item")
    public List<Items> ItemById(long id)
    {
        List<Items> items= new ArrayList<>();
        Items item =repository.findById(id).orElse(null);
        if(item==null){
            throw new ItemNotFoundException("Item with Itemnumber "+id+ " is not present." );
        }
        items.add(item);
        return items;
    }

    @Cacheable(cacheNames = "item")
    public List<Items> GetByOrderId(@PathVariable int ordernumber)
    {
        return repository.findByordernumber(ordernumber);
    }

    public Items InsertItemofOrder(@RequestBody Items items)
    { if (items.getItemdescription()==null|| items.getItemquantity()==0)
        throw new InvalidItemFormat("Itemdescription and Quantity are mandatory field");

//        RestTemplate restTemplate =new RestTemplate();
//        Orders[] l= restTemplate.getForObject("http://localhost:8000/orders",Orders[].class);
//
//        for(Orders i:l)
//        {
//            if(items.getOrdernumber()== i.getOrdernumber()) {
//                Items savedItems = repository.save(items);
//                return savedItems;
//            }
//        }
//        return null;
        RestTemplate restTemplate =new RestTemplate();
        String url= "http://localhost:8000/orders";
        RequestCallback requestCallback = request -> request.getHeaders().
                setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
        ResponseExtractor<Void> responseExtractor = response -> {
            Path path = Paths.get("storing.json");
            Files.copy(response.getBody(), path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(response.getClass());
            return null;
        };
        restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);
        ObjectMapper objectMapper = new ObjectMapper();
        //  objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        try {

            Data data = objectMapper.readValue(new File("storing.json"),
                    new TypeReference<Data>() {});
            OrdersResponse ordersResponse=data.getData();
            List<Orders> orders= ordersResponse.getOrders();

            for (Orders order : orders) {
                if (items.getOrdernumber() == order.getOrdernumber()) {
                    Items savedItem = repository.save(items);
                    return savedItem;
                }

            }
            return null;
        }
        catch (IOException e) {
            System.out.println(e);

        }

        return null;
    }


    @CacheEvict(cacheNames = "item")
    public void deleteOrderItem(@PathVariable long itemno)
    {
        if(repository.findById(itemno)==null){
            throw new ItemNotFoundException("Item with itemnumber "+itemno+ " is not present." );
        }
        repository.deleteById(itemno);
    }

    @CachePut(cacheNames = "item")
    public Items updateItem(@RequestBody Items items)
    {

        RestTemplate restTemplate =new RestTemplate();
        Orders[] l= restTemplate.getForObject("http://localhost:8000/orders",Orders[].class);
        for(Orders i:l)
        {
            if(items.getOrdernumber()==i.getOrdernumber())
            {
                Items updatedItems = repository.save(items);
                return updatedItems;
            }
        }
        return null;

    }

}
