package ch.heig.items.api.endpoints;

import ch.heig.items.api.exceptions.InvalidArgumentException;
import ch.heig.items.api.exceptions.ItemNotFoundException;
import ch.heig.items.api.services.ItemsService;
import org.openapitools.api.ItemsApi;
import org.openapitools.model.Item;
import org.openapitools.model.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ItemsEndPoint implements ItemsApi {
    @Autowired
    private ItemsService itemService;

    @Override
    public ResponseEntity<List<Item>> getItems(){
        List<Item> items = itemService.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addItem(@RequestBody Item item){
        Item itemAdded = itemService.add(item);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Item> getItem(Integer id) {
        Item item = itemService.getById(id);
        if(item == null){
            throw new ItemNotFoundException(id);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> putItem(
            Integer id,
            Item item
    ) {
        if(item.getId() != id){
            throw new InvalidArgumentException();
        }
        Item newItem = itemService.addOrUpdate(item);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> patchItem(
            Integer id,
            String name,
            Sound soundFrotte,
            Sound soundTape,
            List<Sound> soundsTombe
    ) {
        Item responseItem = itemService.update(
                id,
                name,
                soundFrotte,
                soundTape,
                soundsTombe
        );
        return new ResponseEntity<>(responseItem, HttpStatus.OK);
    }
}