package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Object resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage.get(resumeIndex);
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove(resumeIndex);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndexByUuid(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.containsKey(uuid)) return uuid;
        }
        return null;
    }

    @Override
    protected boolean isResumeExist(Object resumeIndex) {
        return resumeIndex != null;
    }
}
