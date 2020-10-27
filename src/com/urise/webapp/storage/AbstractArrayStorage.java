package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        }
        System.out.println("ERROR: Operation fail. Resume " + uuid + " not found");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (resumeIndex >= 0) {
            storage[resumeIndex] = resume;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + resume.getUuid() + " not found");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndexByUuid(String uuid);
}
