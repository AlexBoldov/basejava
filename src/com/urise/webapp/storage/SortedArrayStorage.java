package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        int resumeIndex = getIndexByUuid(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Operation fail. No storage space");
        } else if (resumeIndex < 0) {
            resumeIndex = Math.abs(resumeIndex) - 1;
            System.arraycopy(storage, resumeIndex, storage, resumeIndex + 1, size - resumeIndex);
            storage[resumeIndex] = resume;
            size++;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + resume.getUuid() + " already exists");
        }
    }

    @Override
    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex < 0) {
            System.out.println("ERROR: Operation fail. Resume " + uuid + " not found");
        } else if (size == STORAGE_LIMIT) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - 1 - resumeIndex);
            storage[size - 1] = null;
            size--;
        } else {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex);
            size--;
        }
    }

    @Override
    protected int getIndexByUuid(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
