package notebook.repository;

import notebook.model.User;

import java.util.List;
import java.util.Optional;

public interface GBRepository<E, I> {
    List<E> findAll();
    E create(E e);
    Optional<E> findById(List<User> users, I id);
    Optional<E> update(I id, E e);
    boolean delete(List<User> allUser, Long id);


}
