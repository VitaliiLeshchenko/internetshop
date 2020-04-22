package mate.academy.internetshop.dao.iml;

import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        return null;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.getBuckets().stream()
                .filter(bucket -> bucket.getId().equals(bucketId))
                .findFirst().orElseThrow();
    }
}
