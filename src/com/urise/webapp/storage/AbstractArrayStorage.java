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
    protected void updateResume(Resume resume, int resumeIndex) {
        storage[resumeIndex] = resume;
    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Operation fail. No storage space", resume.getUuid());
        } else {
            saveResumeArray(resume, resumeIndex);
            size++;
        }
    }

    @Override
    protected Resume getResume(int resumeIndex) {
        return storage[resumeIndex];
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        deleteResumeArray(resumeIndex);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void saveResumeArray(Resume resume, int resumeIndex);

    protected abstract void deleteResumeArray(int resumeIndex);
}
