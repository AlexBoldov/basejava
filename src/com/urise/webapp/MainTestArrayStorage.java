package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //  private final static Storage ARRAY_STORAGE = new ArrayStorage();
    //  private final static Storage ARRAY_STORAGE = new SortedArrayStorage();
    //  private final static Storage ARRAY_STORAGE = new ListStorage();
    private final static Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");
//      final Resume r4 = new Resume("uuid3");
        final Resume r5 = new Resume("uuid1");

        System.out.println("Save r1 ...");
        ARRAY_STORAGE.save(r1);
        System.out.println("Save r2 ...");
        ARRAY_STORAGE.save(r2);
        System.out.println("Save r3 ...");
        ARRAY_STORAGE.save(r3);
/*
        System.out.println("Save r4 ...");
        ARRAY_STORAGE.save(r4);
*/
        System.out.println("Get r1 ...");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
/*
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Get dummy ...");
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
*/
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Update r1 ...");
        ARRAY_STORAGE.update(r1);
        System.out.println("Delete r1 ...");
        ARRAY_STORAGE.delete(r1.getUuid());
/*
        System.out.println("Update r1 ...");
        ARRAY_STORAGE.update(r1);
        System.out.println("Delete r1 ...");
        ARRAY_STORAGE.delete(r1.getUuid());

        System.out.println("Get r1 ...");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
*/
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Save r5 ...");
        ARRAY_STORAGE.save(r5);

        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Clear ...");
        ARRAY_STORAGE.clear();

        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
