package uma.fitpro.utils;

import lombok.Setter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;

public class SortedList<T> extends AbstractList<T> {

    private ArrayList<T> list;

    @Setter
    private Comparator<T> comparator;

    public SortedList(Comparator<T> comparator) {
        setList(new ArrayList<>());
        setComparator(comparator);
    }

    public SortedList(ArrayList<T> list, Comparator<T> comparator) {
        setComparator(comparator);
        setList(list);
    }

    private void sort(){
        list.sort(comparator);
    }

    private void setList(ArrayList<T> list) {
        this.list = list;
        sort();
    }

    @Override
    public void add(int index, T element) {
        list.add(index, element);
        sort();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
