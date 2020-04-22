package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public class Storage {
    private static long itemId = 0;
    private static long bucketId = 0;
    private static final List<Item> items = new ArrayList<>();
    private static final List<Bucket> buckets = new ArrayList<>();

    public static Item addItemToList(Item item) {
        item.setId(itemId);
        items.add(item);
        itemId++;
        return item;
    }

    public static Bucket addBucketToList(Bucket bucket) {
        bucket.setId(bucketId);
        bucketId++;
        return bucket;
    }

    public static List<Item> getItems() {
        return items;
    }

    public static List<Bucket> getBuckets() {
        return buckets;
    }
}
