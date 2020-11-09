package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        updateResume(resume, getSearchKeyIfResumeExist(resume.getUuid()));
    }

    protected abstract void updateResume(Resume resume, SK searchKey);

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        saveResume(resume, getSearchKeyIfResumeNotExist(resume.getUuid()));
    }

    protected abstract void saveResume(Resume resume, SK searchKey);

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(getSearchKeyIfResumeExist(uuid));
    }

    protected abstract Resume getResume(SK searchKey);

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteResume(getSearchKeyIfResumeExist(uuid));
    }

    protected abstract void deleteResume(SK searchKey);

    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted");
        List<Resume> storage = copyStorageToList();
        storage.sort(RESUME_COMPARATOR);
        return storage;
    }

    protected abstract List<Resume> copyStorageToList();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isResumeExist(SK searchKey);

    private SK getSearchKeyIfResumeExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isResumeExist(searchKey)) {
            LOG.warning("Operation fail. Resume " + uuid + " not found");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfResumeNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isResumeExist(searchKey)) {
            LOG.warning("Operation fail. Resume " + uuid + " already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
