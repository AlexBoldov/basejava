package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final ArrayList<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public void updateResume(Resume resume, int resumeIndex) {
        storage.set(resumeIndex, resume);
    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
        storage.add(resume);
    }

    @Override
    protected Resume getResume(int resumeIndex) {
        return storage.get(resumeIndex);
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        storage.remove(resumeIndex);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndexByUuid(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) return i;
        }
        return -1;
    }

}
