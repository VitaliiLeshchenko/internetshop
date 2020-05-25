package mate.academy.internet_shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T element);

    Optional<T> get(K id);

    T update(T element);

    boolean delete(K id);

    List<T> getAll();
}
