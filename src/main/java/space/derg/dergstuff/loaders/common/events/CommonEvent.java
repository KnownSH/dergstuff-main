package space.derg.dergstuff.loaders.common.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommonEvent<T> {
    protected final List<Consumer<T>> listeners = new ArrayList<>();

    public CommonEvent() {}

    public void addListener(Consumer<T> listener) {
        listeners.add(listener);
    }

    public void invoke(T event) {
        for (Consumer<T> listener : listeners) {
            listener.accept(event);
        }
    }
}