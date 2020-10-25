package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int uuidIndex = getIndexByUuid(resume.getUuid());
        if (uuidIndex != -1) {
            storage[uuidIndex] = resume;
        } else {
            System.out.println("ERROR: Operation fail. Resume not found");
        }
    }

    public void save(Resume resume) {
        if (!isStorageFull() && getIndexByUuid(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Operation fail. Resume already exists");
        }
    }

    public Resume get(String uuid) {
        int uuidIndex = getIndexByUuid(uuid);
        if (uuidIndex != -1) {
            return storage[uuidIndex];
        }
        System.out.println("ERROR: Operation fail. Resume not found");
        return null;
    }

    public void delete(String uuid) {
        int uuidIndex = getIndexByUuid(uuid);
        if (uuidIndex != -1) {
            storage[uuidIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Operation fail. Resume not found");
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

    private int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    private boolean isStorageFull() {
        if (size < storage.length) return false;
        System.out.println("ERROR: Operation fail. No storage space");
        return true;
    }
}
