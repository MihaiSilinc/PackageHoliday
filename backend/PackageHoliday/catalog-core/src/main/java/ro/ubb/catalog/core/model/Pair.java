package ro.ubb.catalog.core.model;

import java.io.Serializable;
import java.util.Objects;

public class Pair<firstObject extends Serializable, secondObject extends Serializable> implements Serializable {
    firstObject first;
    secondObject second;

    public Pair(firstObject first, secondObject second) {
        this.first = first;
        this.second = second;
    }

    public firstObject getFirst() {
        return first;
    }

    public void setFirst(firstObject first) {
        this.first = first;
    }

    public secondObject getSecond() {
        return second;
    }

    public void setSecond(secondObject second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
