package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, resumeIndex);
        }
    }

    protected abstract void updateResume(Resume resume, int resumeIndex);

    public void save(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (resumeIndex < 0) {
            saveResume(resume, resumeIndex);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract void saveResume(Resume resume, int resumeIndex);

    public Resume get(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(resumeIndex);
    }

    protected abstract Resume getResume(int resumeIndex);

    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(resumeIndex);
        }
    }

    protected abstract void deleteResume(int resumeIndex);

    protected abstract int getIndexByUuid(String uuid);
}
