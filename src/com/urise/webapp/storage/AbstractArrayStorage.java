package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Operation fail. No storage space", resume.getUuid());
        }
        saveToStorage(resume, searchKey);
        size++;
    }

    protected abstract void saveToStorage(Resume resume, int resumeIndex);

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        deleteFromStorage(searchKey);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteFromStorage(int resumeIndex);

    @Override
    protected List<Resume> copyStorageToList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean isResumeExist(Integer searchKey) {
        return searchKey >= 0;
    }
}
