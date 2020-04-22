package mate.academy.internetshop.dao.iml;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        return Storage.addItemToList(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.getItems()
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        if (item == null) {
            return null;
        }
        Item oldItem = Storage.getItems().stream()
                .filter(item1 -> item1.getId().equals(item.getId()))
                .findFirst()
                .orElse(item);
        int indexOfOldItem = Storage.getItems().indexOf(oldItem);
        return indexOfOldItem == -1 ? create(item) : Storage.getItems().set(indexOfOldItem, item);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getItems()
                .remove(Storage.getItems()
                .stream()
                .filter(item1 -> item1.getId().equals(id))
                .findFirst()
                .orElse(null));
    }

    @Override
    public boolean delete(Item item) {
        return Storage.getItems().remove(item);
    }

    @Override
    public List<Item> getAll() {
        return Storage.getItems();
    }

}
