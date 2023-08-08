package org.example;


import java.util.Iterator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(232, "Климов Андрей Викторович", "+375337894563"));
        contactList.addToEnd((new Contact(631,"Смирнова Ольга Ивановна", "+375253697845")));
        contactList.addToEnd(new Contact(732, "Скворцов Геннадий Иванович", "+375296321772"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for (Object contact : contactList) {
            System.out.println(contact);
        }

    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
    /**
     * Класс списка
     */

    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;
                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        /**
         * Класс отельного элемента
         */
        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка на наличие пустого значения в начале(в голове) списка
        public boolean isEmpty(){
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если голова и хвост пусты, то присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            // Если голова не пуста, то передаём элементу адрес и ставим его в хвост
            } else {
                tail.next = newItem;
                tail = newItem;
            }

        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) { // Если не пуста голова и не равна 0
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }

            }
        }


    }
}