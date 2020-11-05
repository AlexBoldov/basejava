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
        int transformIndex = Math.abs(resumeIndex) - 1;
        storage.add(transformIndex, resume);
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
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(storage, searchKey);
    }

}
