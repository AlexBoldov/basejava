package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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
        if (resumeIndex != -1) {
            return storage[resumeIndex];
        }
        System.out.println("ERROR: Operation fail. Resume " + uuid + " not found");
        return null;
    }

    protected abstract int getIndexByUuid(String uuid);
}
