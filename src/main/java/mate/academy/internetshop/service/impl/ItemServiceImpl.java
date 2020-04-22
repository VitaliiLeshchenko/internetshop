package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id).orElseThrow();
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }
}
