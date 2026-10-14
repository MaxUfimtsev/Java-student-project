import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CarArrayList {
    private Object[] list;
    private int size;

    public CarArrayList() {
        this.list = new Object[10];
        this.size = 0;
    }

    public boolean add(Object obj) {
        boolean result = false;
        if (this.size < list.length){
            list[size] = obj;
            size++;
            result = true;
        }
        else {
            this.list = expandSpace();
            list[size] = obj;
            size++;
            result = true;
        }

        return result;
    }

    private Object[] expandSpace() {

        Object[] newList = new Object[size*2];

        for (var i = 0; i < size; i++){
            newList[i] = this.list[i];
        }

        return newList;

    }

    public Object get(int index) {

        if (index >= 0 && index < this.size) {
            return this.list[index];
        }

        throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы коллекции.");
    }

    public Object remove(int index) {

        if (index >= 0 && index < this.size) {
            Object savedElement = this.list[index];
            shiftElements(this.list, index);
            size--;
            return savedElement;
        }

        throw new IndexOutOfBoundsException("Индекс выходит за пределы коллекции.");
    }

    private void shiftElements(Object[] list, int index) {
        for (var i = index; i < this.size-1; i++) {
            list[i] = list[i+1];
        }
        list[size-1] = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addAll(Iterable<?> list) {
        for (var i: list) {
            this.add(i);
        }
    }

    public Stream<Car> stream() {
        return Arrays.stream(this.list, 0, this.size)
                .map(Car.class::cast);
    }
}
