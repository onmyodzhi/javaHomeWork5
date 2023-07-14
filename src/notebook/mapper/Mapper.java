package notebook.mapper;
public interface Mapper<E, T> {
    T toInput(E e);
    E toOutput(T t);
}
