package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public void updateResume(Resume resume, Object resumeIndex) {
        storage.set((int) resumeIndex, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object resumeIndex) {
        storage.add(resume);
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage.get((int) resumeIndex);
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove((int) resumeIndex);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndexByUuid(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) return i;
        }
        return -1;
    }

    @Override
    protected boolean isResumeExist(Object resumeIndex) {
        return (int) resumeIndex >= 0;
    }

}