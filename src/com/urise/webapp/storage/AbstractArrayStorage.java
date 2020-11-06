package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage[(int) resumeIndex] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Object resumeIndex) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Operation fail. No storage space", resume.getUuid());
        } else {
            saveToStorage(resume, (int) resumeIndex);
            size++;
        }
    }

    protected abstract void saveToStorage(Resume resume, int resumeIndex);

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage[(int) resumeIndex];
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        deleteFromStorage((int) resumeIndex);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteFromStorage(int resumeIndex);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isResumeExist(Object resumeIndex) {
        return (int) resumeIndex >= 0;
    }
}
