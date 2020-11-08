package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void update(Resume resume) {
        updateResume(resume, getSearchKeyIfResumeExist(resume.getUuid()));
    }

    protected abstract void updateResume(Resume resume, Object searchKey);

    public void save(Resume resume) {
        saveResume(resume, getSearchKeyIfResumeNotExist(resume.getUuid()));
    }

    protected abstract void saveResume(Resume resume, Object searchKey);

    public Resume get(String uuid) {
        return getResume(getSearchKeyIfResumeExist(uuid));
    }

    protected abstract Resume getResume(Object searchKey);

    public void delete(String uuid) {
        deleteResume(getSearchKeyIfResumeExist(uuid));
    }

    protected abstract void deleteResume(Object searchKey);

    public List<Resume> getAllSorted() {
        List<Resume> storage = copyStorageToList();
        storage.sort(RESUME_COMPARATOR);
        return storage;
    }

    protected abstract List<Resume> copyStorageToList();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isResumeExist(Object searchKey);

    private Object getSearchKeyIfResumeExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isResumeExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfResumeNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isResumeExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
