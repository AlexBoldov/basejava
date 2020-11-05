package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) return i;
        }
        return -1;
    }

    @Override
    protected void saveResumeArray(Resume resume, int resumeIndex) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResumeArray(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }
}
