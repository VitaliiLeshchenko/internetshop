package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.dao.iml.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.Service;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private BucketDao bucketDao;

    @Inject
    private ProductDao productDao;

    @Override
    public Bucket adItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Product product = productDao.get(itemId).orElseThrow();
        return null;
    }
}
