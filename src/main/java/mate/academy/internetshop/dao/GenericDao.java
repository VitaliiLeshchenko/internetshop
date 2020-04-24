package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T user);

    Optional<T> get(K id);

    T update(T user);

    boolean delete(K id);

    List<T> getAll();
}
