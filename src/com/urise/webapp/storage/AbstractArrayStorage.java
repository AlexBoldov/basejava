package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[resumeIndex] = resume;
        }
    }

    public void save(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Operation fail. No storage space", resume.getUuid());
        } else if (resumeIndex < 0) {
            saveResume(resume, resumeIndex);
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[resumeIndex];
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(resumeIndex);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndexByUuid(String uuid);

    protected abstract void saveResume(Resume resume, int resumeIndex);

    protected abstract void deleteResume(int resumeIndex);
}
