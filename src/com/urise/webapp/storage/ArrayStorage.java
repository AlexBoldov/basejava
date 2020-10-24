package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (searchIndex(resume.getUuid()) != -1) {
            storage[searchIndex(resume.getUuid())] = resume;
        }
    }

    public void save(Resume resume) {
        if (!isStorageFull() && isUuidUnique(resume.getUuid())) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (searchIndex(uuid) != -1) {
            return storage[searchIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (searchIndex(uuid) != -1) {
            storage[searchIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        System.out.println("ERROR: Operation fail. Resume not found");
        return -1;
    }

    private boolean isUuidUnique(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("ERROR: Operation fail. Resume already exists");
                return false;
            }
        }
        return true;
    }

    private boolean isStorageFull() {
        if (storage.length != size) return false;
        System.out.println("ERROR: Operation fail. No storage space");
        return true;
    }
}
