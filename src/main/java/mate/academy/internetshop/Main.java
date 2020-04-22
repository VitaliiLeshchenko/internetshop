package mate.academy.internetshop;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;

public class Main {
    static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        Item item = new Item("n1", 23.0, 100);
        Item item2 = new Item("n2", 2.35, 10);

        ItemDao itemDao = (ItemDao)injector.getInstance(ItemDao.class);

        item = itemDao.create(item);
        item2 = itemDao.create(item2);

        System.out.println(Storage.getItems());

        item2.setName("updated n2");
        item2 = itemDao.update(item2);
        Item item3 = itemDao.update(new Item("n3", 39.0, 809));

        System.out.println(Storage.getItems());

        System.out.println(itemDao.delete(100L));

    }
}
