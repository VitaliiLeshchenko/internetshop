package mate.academy.internetshop.dao.iml;

import mate.academy.internetshop.model.Bucket;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);
}
