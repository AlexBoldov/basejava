package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

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
