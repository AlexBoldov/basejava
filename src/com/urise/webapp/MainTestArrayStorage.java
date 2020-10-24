package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid3");

        System.out.println("Save r1 ...");
        ARRAY_STORAGE.save(r1);
        System.out.println("Save r2 ...");
        ARRAY_STORAGE.save(r2);
        System.out.println("Save r3 ...");
        ARRAY_STORAGE.save(r3);
        System.out.println("Save r4 ...");
        ARRAY_STORAGE.save(r4);

        System.out.println("Get r1 ...");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy ...");
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Update r1 ...");
        ARRAY_STORAGE.update(r1);
        System.out.println("Delete r1 ...");
        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println("Update r1 ...");
        ARRAY_STORAGE.update(r1);
        System.out.println("Delete r1 ...");
        ARRAY_STORAGE.delete(r1.getUuid());

        System.out.println("Get r1 ...");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));

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