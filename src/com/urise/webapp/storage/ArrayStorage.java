package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + resume.getUuid() + " not found");
        }
    }

    public void save(Resume resume) {
        if (!isStorageFull() && getIndexByUuid(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + resume.getUuid() + " already exists");
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) return i;
        }
        return -1;
    }

    private boolean isStorageFull() {
        if (size < STORAGE_LIMIT) return false;
        System.out.println("ERROR: Operation fail. No storage space");
        return true;
    }
}
