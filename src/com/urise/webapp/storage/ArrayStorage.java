package com.urise.webapp.storage;

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
    protected int defineIndexToSave(int resumeIndex) {
        return size;
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }
}
