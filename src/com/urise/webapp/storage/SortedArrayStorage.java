package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndexByUuid(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResumeArray(Resume resume, int resumeIndex) {
        resumeIndex = Math.abs(resumeIndex) - 1;
        System.arraycopy(storage, resumeIndex, storage, resumeIndex + 1, size - resumeIndex);
        storage[resumeIndex] = resume;
    }

    @Override
    protected void deleteResumeArray(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - 1 - resumeIndex);
    }
}
