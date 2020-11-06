package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object resumeIndex = getIndexByUuid(resume.getUuid());
        if (!isResumeExist(resumeIndex)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, resumeIndex);
        }
    }

    protected abstract void updateResume(Resume resume, Object resumeIndex);

    public void save(Resume resume) {
        Object resumeIndex = getIndexByUuid(resume.getUuid());
        if (!isResumeExist(resumeIndex)) {
            saveResume(resume, resumeIndex);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract void saveResume(Resume resume, Object resumeIndex);

    public Resume get(String uuid) {
        Object resumeIndex = getIndexByUuid(uuid);
        if (!isResumeExist(resumeIndex)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(resumeIndex);
    }

    protected abstract Resume getResume(Object resumeIndex);

    public void delete(String uuid) {
        Object resumeIndex = getIndexByUuid(uuid);
        if (!isResumeExist(resumeIndex)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(resumeIndex);
        }
    }

    protected abstract void deleteResume(Object resumeIndex);

    protected abstract Object getIndexByUuid(String uuid);

    protected abstract boolean isResumeExist(Object resumeIndex);
}
